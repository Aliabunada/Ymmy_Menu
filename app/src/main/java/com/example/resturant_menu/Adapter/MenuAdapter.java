package com.example.resturant_menu.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resturant_menu.DB.Mnuedb;
import com.example.resturant_menu.R;
import com.example.resturant_menu.models.DbBitmapUtility;
import com.example.resturant_menu.models.Menu_Categories;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter  {
   ArrayList<Menu_Categories> menuarraylist = new ArrayList<>();
   Context context;
   LayoutInflater layoutInflater;
    Mnuedb menuedb;

    public MenuAdapter(ArrayList<Menu_Categories> menuarraylist, Context context) {

        this.menuarraylist = menuarraylist;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return menuarraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return menuarraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return menuarraylist.get(i).getItem_id();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View root = layoutInflater.inflate(R.layout.menu_item,null);
        ImageView imageView = root.findViewById(R.id.custom_menu_item_iv_photo);
        TextView txttitle = root.findViewById(R.id.custom_menu_item_tv_title);
        TextView txtdesc = root.findViewById(R.id.custom_menu_item_tv_desc);
        TextView txtprice = root.findViewById(R.id.custom_menu_item_tv_price);

        //   converting the Image type and show it from database to lis
//       byte[] outImg = menuarraylist.get(i).getItem_image();
//        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImg);
//        Bitmap theImage = BitmapFactory.decodeStream(imageStream,0,outImg.length);


        byte[] byteArray = menuarraylist.get(i).getItem_image();

        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);

        imageView.setImageBitmap(bm);
//        imageView.setImageBitmap(DbBitmapUtility.getImage(menuarraylist.get(i).getItem_image()));
        txttitle.setText(menuarraylist.get(i).getItem_title().toString());
        txtdesc.setText(menuarraylist.get(i).getItem_description().toString());
        txtprice.setText(String.valueOf(menuarraylist.get(i).getItem_price()));
        return root;
    }
}
