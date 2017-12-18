package com.fanwe.lib.utils;


import android.content.Context;
import android.util.DisplayMetrics;

public final class FResUtil
{
    private FResUtil()
    {
    }


    public static DisplayMetrics getDisplayMetrics(Context context)
    {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * sp转px
     *
     * @param sp
     * @param context
     * @return
     */
    public static int sp2px(float sp, Context context)
    {
        final float fontScale = getDisplayMetrics(context).scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    /**
     * dp转px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dp2px(float dp, Context context)
    {
        final float scale = getDisplayMetrics(context).density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param px
     * @param context
     * @return
     */
    public static int px2dp(float px, Context context)
    {
        final float scale = getDisplayMetrics(context).density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context)
    {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return metrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return metrics.heightPixels;
    }

    /**
     * 获得屏幕宽度的百分比
     *
     * @param percent [0-1]
     * @return
     */
    public static int getScreenWidthPercent(float percent, Context context)
    {
        return (int) ((float) getScreenWidth(context) * percent);
    }

    /**
     * 获得屏幕高度的百分比
     *
     * @param percent [0-1]
     * @return
     */
    public static int getScreenHeightPercent(float percent, Context context)
    {
        return (int) ((float) getScreenHeight(context) * percent);
    }
}
