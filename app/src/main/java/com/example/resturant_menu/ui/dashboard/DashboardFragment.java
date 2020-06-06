package com.example.resturant_menu.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.resturant_menu.DB.Mnuedb;
import com.example.resturant_menu.MainActivity;
import com.example.resturant_menu.R;
import com.example.resturant_menu.models.DbBitmapUtility;
import com.example.resturant_menu.models.Menu_Categories;
import com.example.resturant_menu.ui.home.HomeFragment;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Mnuedb mnuedb;
    final int REQUEST_CODE_GALLERY = 1000;
    Bitmap bitmapImage = null;
    ImageView imageViewchoosen;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final Button button = root.findViewById(R.id.btn_show_Dialog);
        final Button btndelete = root.findViewById(R.id.btn_deleteitem);

        mnuedb = new Mnuedb(getContext());
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final   Dialog dialog = new Dialog(getContext());
                final String passwords = "admin";
                dialog.setCancelable(true);
                dialog.setTitle("DELETING ITEM FROM MENUE");
                dialog.setContentView(R.layout.delete_dialog);
                final EditText edtpass2 = dialog.findViewById(R.id.edt_pass2);
                final EditText edtindex = dialog.findViewById(R.id.edt_indexofitem);
                final Button btn_deleting = dialog.findViewById(R.id.btn_deleting);
                btn_deleting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtpass2.getText().toString().equals(passwords) ) {
                            int item = Integer.parseInt(edtindex.getText().toString());
                            if (mnuedb.deleteItem(item) == true ){
                                Toast.makeText(getContext(), "Deleted Done ^_^", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getContext(), "Faild ^_^", Toast.LENGTH_SHORT).show();
                            }

                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(getContext(), "Faild ^_^", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              final   Dialog dialog = new Dialog(getContext());
              final String passwords = "admin";
                dialog.setCancelable(true);
                dialog.setTitle("ADDING NEW ITEM TO MENUE");
                dialog.setContentView(R.layout.add_dialog);
                final EditText edtitle = dialog.findViewById(R.id.edt_title);
                final EditText edtprice = dialog.findViewById(R.id.edt_price);
                final EditText edtdescreption = dialog.findViewById(R.id.edt_descreption);
                final Button btn_addimg = dialog.findViewById(R.id.btn_addimg);
                final EditText edpassword = dialog.findViewById(R.id.edt_pass);
              imageViewchoosen = dialog.findViewById(R.id.imageViewchoosen);

               Button btn_save = dialog.findViewById(R.id.btnsave);


                btn_addimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                         Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                         startActivityForResult(pickPhoto , REQUEST_CODE_GALLERY);


                    }
                });
                    btn_save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (edpassword.getText().toString().equals(passwords)) {
                                String title = edtitle.getText().toString();
                                int price = Integer.parseInt(edtprice.getText().toString());
                                String decreption = edtdescreption.getText().toString();
                                Drawable drawable = imageViewchoosen.getDrawable();

                                if(drawable!=null) {
                                    Bitmap bitmapDrawable = ((BitmapDrawable) drawable).getBitmap();
                                    if (mnuedb.insertItem(title, decreption, getBytes(bitmapDrawable), price) == true)
                                    {
                                    Toast.makeText(getContext(), "Added Done ^_^", Toast.LENGTH_SHORT).show();

                                    }
                                   } else {
                                    Toast.makeText(getContext(), "Faild", Toast.LENGTH_SHORT).show();

                                }
                                dialog.dismiss();

                            }
                            else {
                                Toast.makeText(getContext(), "The Password Incorrect", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


dialog.show();
            }

        });

        return root;

    }

    //convert bitmap to Blob to store it in DB

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();

                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageViewchoosen.setImageBitmap(bitmapImage);
            }
        }


    }

}
