package com.example.resturant_menu.models;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class DbBitmapUtility {

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static byte[] ToArraybyte(Bitmap b)
    {
        int bytes = b.getByteCount();

        ByteBuffer buffer = ByteBuffer.allocate(bytes); //Create a new buffer
        b.copyPixelsToBuffer(buffer); //Move the byte data to the buffer

        // DECODE
        String imgString = Base64.encodeToString(buffer.array(), Base64.NO_WRAP);
        byte[] ret = Base64.decode(imgString, Base64.DEFAULT);
        imgString = null;

        return ret;
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }



}
/*
Before inserting into database, you need to convert your Bitmap image into byte array first then apply it using database query.
When retrieving from database, you certainly have a byte array of image,
    what you need to do is to convert byte array back to original image.
     So, you have to make use of BitmapFactory to decode

 */
