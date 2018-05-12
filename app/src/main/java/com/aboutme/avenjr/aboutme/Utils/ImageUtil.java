package com.aboutme.avenjr.aboutme.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by root on 14/8/17.
 */

public class ImageUtil {

    public static void saveBitmap(Bitmap bitmap, String filename,int quality) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Environment.getExternalStorageDirectory() + "/" + filename);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFromFile(String filename) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(Environment.getExternalStorageDirectory() + "/" + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap roundImage(Bitmap bitmap) {
        Bitmap output;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            output = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        float r = 0;

        if (bitmap.getWidth() > bitmap.getHeight()) {
            r = bitmap.getHeight() / 2;
        } else {
            r = bitmap.getWidth() / 2;
        }

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(r, r, r, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

}
