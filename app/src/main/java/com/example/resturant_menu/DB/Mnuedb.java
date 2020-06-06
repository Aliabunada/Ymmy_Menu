package com.example.resturant_menu.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.resturant_menu.models.DbBitmapUtility;
import com.example.resturant_menu.models.Menu_Categories;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class Mnuedb extends SQLiteOpenHelper {
    SQLiteDatabase db;
    DbBitmapUtility dbBitmapUtility;

    public static  final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "YUMMYMENU";
    public Mnuedb(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(Menu_Categories.CREATE_TABLE);

        }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Menu_Categories.TABELNAME);
    onCreate(sqLiteDatabase);
    }
    //Insert Data


    public boolean insertItem(String title,String description,  byte[] Image,int price){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Menu_Categories.TITLE,title);
        contentValues.put(Menu_Categories.IMAGE,Image);
        contentValues.put(Menu_Categories.DESCREPTION,description);
        contentValues.put(Menu_Categories.PRICE,price);

        long count = db.insert(Menu_Categories.TABELNAME,null,contentValues);
        return count > 0;
    }

    //Getting all items
    public ArrayList<Menu_Categories> getAllMENUE (){
        ArrayList<Menu_Categories> menlist = new ArrayList<>();
        String sqlquery = "select * from " +Menu_Categories.TABELNAME +" order by "+ Menu_Categories.ID;
        Cursor c = db.rawQuery(sqlquery,null);
        if (c.moveToFirst()){
            do {

                Menu_Categories menu_categories = new Menu_Categories();

                menu_categories.setItem_id(c.getInt(c.getColumnIndex(Menu_Categories.ID)));
                menu_categories.setItem_price(c.getInt(c.getColumnIndex(Menu_Categories.PRICE)));
                menu_categories.setItem_description(c.getString(c.getColumnIndex(Menu_Categories.DESCREPTION)));
                menu_categories.setItem_image(c.getBlob(c.getColumnIndex(Menu_Categories.IMAGE)));
                menu_categories.setItem_title(c.getString(c.getColumnIndex(Menu_Categories.TITLE)));
                menlist.add(menu_categories);

            }while (c.moveToNext());
            c.close();
        }
        return menlist;
    }
    // Deleting Item
public boolean deleteItem(int id){
        int count = db.delete(Menu_Categories.TABELNAME,Menu_Categories.ID +"= ?",new String[]{String.valueOf(id)});
        return count > 0;

    }
    // Delete all items
    public void deleteall (){
        db.execSQL("delete from "+ Menu_Categories.TABELNAME);
        db.close();
    }

    // Update Item
    public boolean updateitem(int id,String title,String description, byte[] Image, int price){
        ContentValues cv = new ContentValues();
        cv.put(Menu_Categories.TITLE,title);
        cv.put(Menu_Categories.IMAGE,Image);
        cv.put(Menu_Categories.DESCREPTION,description);
        cv.put(Menu_Categories.PRICE,price);

        long count = db.update(Menu_Categories.TABELNAME,cv,Menu_Categories.ID+ "=?",new String[]{String.valueOf(id)});
        return count > 0;
    }

    //db close
    public void closeDb(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db != null && db.isOpen())
            db.close();
    }

}
