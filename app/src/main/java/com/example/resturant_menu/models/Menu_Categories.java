package com.example.resturant_menu.models;

import android.graphics.Bitmap;

public class Menu_Categories {
//    private int item_category_id ;
    private int item_id ;
    private String item_description ;
    private byte[]   item_image;
    private int item_price ;
    private String item_title  ;
 public static final String TABELNAME = "YUMMYMENU";

    public static final String ID = "item_id";
    public static final String IMAGE = "item_image";
    public static final String PRICE = "item_price";
    public static final String TITLE = "item_title";
    public static final String DESCREPTION = "item_description";

    public static final String CREATE_TABLE = "CREATE TABLE "+TABELNAME +
            "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            IMAGE+"  BLOB, "+
            DESCREPTION+" TEXT NOT NULL,"+
            TITLE+" TEXT NOT NULL,"
            +PRICE+" INTEGER"+")";


    public Menu_Categories() {
    }


    public Menu_Categories( String item_description, int item_id, byte[] item_image, int item_price, String item_title) {

        this.item_description = item_description;
        this.item_id = item_id;
        this.item_image = item_image;
        this.item_price = item_price;
        this.item_title = item_title;
    }



    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public byte[]   getItem_image() {
        return item_image;
    }

    public void setItem_image(byte[]  item_image) {
        this.item_image = item_image;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }
}
