package com.sd.lib.utils;

import android.content.Context;
import android.content.res.Resources;
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
import android.text.TextUtils;
import android.util.TypedValue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FImageUtil
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
     * @param width
     * @param height
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable, int width, int height)
    {
        if (width <= 0)
            width = drawable.getIntrinsicWidth();

        if (height <= 0)
            height = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, width, height);

        final Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);

        drawable.draw(new Canvas(bitmap));
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
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
            return false;

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            bitmap.compress(format, quality, fos);
            fos.flush();
            return true;
        } catch (Exception e)
        {
            return false;
        } finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                }
            }
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
        if (bytes == null || bytes.length <= 0)
            return null;

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * 把Bitmap转为灰色
     *
     * @param bitmap
     * @param recycle
     * @return
     */
    public static Bitmap grayBitmap(Bitmap bitmap, boolean recycle)
    {
        if (bitmap == null)
            return null;

        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        final Bitmap bitmapGray = Bitmap.createBitmap(width, height, Config.RGB_565);

        final Canvas canvas = new Canvas(bitmapGray);

        final ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        final Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        if (recycle)
            bitmap.recycle();

        return bitmapGray;
    }

    public static BitmapFactory.Options inJustDecodeBounds(String path)
    {
        if (TextUtils.isEmpty(path))
            return null;

        try
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            return options;
        } catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 根据图片资源id获得对应的Bitmap
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap getBitmapFromRes(Context context, int resId)
    {
        if (resId == 0)
            return null;

        final Resources resources = context.getResources();
        final TypedValue value = new TypedValue();
        resources.openRawResource(resId, value);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTargetDensity = value.density;
        final Bitmap bitmap = BitmapFactory.decodeResource(resources, resId, options);
        return bitmap;
    }
}
