package com.fanwe.lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public final class FImageUtil
{
    private FImageUtil()
    {
    }

    /**
     * bitmap转drawable
     *
     * @param bitmap
     * @param context
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap, Context context)
    {
        if (context == null)
        {
            return new BitmapDrawable(bitmap);
        } else
        {
            return new BitmapDrawable(context.getResources(), bitmap);
        }
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap转字节数组
     *
     * @param bitmap
     * @param format  图片格式
     * @param quality 质量[0-100]
     * @return
     */
    public static byte[] bitmapToBytes(Bitmap bitmap, Bitmap.CompressFormat format, int quality)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, quality, baos);
        return baos.toByteArray();
    }

    /**
     * bitmap转File
     *
     * @param bitmap
     * @param file
     * @param format
     * @param quality
     * @return
     */
    public static boolean bitmapToFile(Bitmap bitmap, File file, Bitmap.CompressFormat format, int quality)
    {
        if (bitmap == null || file == null)
        {
            return false;
        }

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            bitmap.compress(format, quality, fos);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 直接数组转bitmap
     *
     * @param bytes
     * @return
     */
    public static Bitmap bytesToBitmap(byte[] bytes)
    {
        if (bytes != null && bytes.length > 0)
        {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else
        {
            return null;
        }
    }

    public static Bitmap grayBitmap(Bitmap bitmap, boolean recycle)
    {
        if (bitmap == null)
        {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap bmpGray = Bitmap.createBitmap(width, height, Config.RGB_565);
        Canvas canvas = new Canvas(bmpGray);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(f);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        if (recycle)
        {
            bitmap.recycle();
        }
        return bmpGray;
    }
}
