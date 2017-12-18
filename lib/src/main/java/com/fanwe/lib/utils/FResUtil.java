package com.fanwe.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public final class FResUtil
{
    private FResUtil()
    {
    }

    private static DisplayMetrics getDisplayMetrics(Context context)
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

    /**
     * 获得状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 返回activity的状态栏高度<br>
     * 如果该activity的状态栏可见则返回状态栏高度，如果不可见则返回0
     *
     * @param context
     * @return
     */
    public static int getActivityStatusBarHeight(Context context)
    {
        if (context == null)
        {
            return 0;
        }
        if (!(context instanceof Activity))
        {
            return getStatusBarHeight(context);
        }

        if (isStatusBarVisible((Activity) context))
        {
            return getStatusBarHeight(context);
        } else
        {
            return 0;
        }
    }

    /**
     * 状态栏是否可见
     *
     * @param activity
     * @return
     */
    public static boolean isStatusBarVisible(Activity activity)
    {
        if (activity == null)
        {
            return false;
        }
        return ((activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == 0);
    }

    /**
     * 获得图片文件名对应的资源id
     *
     * @param drawableName
     * @return
     */
    public static int getDrawableIdentifier(String drawableName, Context context)
    {
        try
        {
            return context.getResources().getIdentifier(drawableName, "drawable",
                    context.getPackageName());
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
