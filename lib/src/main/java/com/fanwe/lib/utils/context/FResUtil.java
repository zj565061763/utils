package com.fanwe.lib.utils.context;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;

public final class FResUtil extends FContext
{
    private FResUtil()
    {
    }

    /**
     * 返回Resources资源对象
     *
     * @return
     */
    public static Resources getResources()
    {
        return get().getResources();
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static int sp2px(float sp)
    {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dp2px(float dp)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param px
     * @return
     */
    public static int px2dp(float px)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth()
    {
        return getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight()
    {
        return getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获得屏幕宽度的百分比
     *
     * @param percent [0-1]
     * @return
     */
    public static int getScreenWidthPercent(float percent)
    {
        return (int) ((float) getScreenWidth() * percent);
    }

    /**
     * 获得屏幕高度的百分比
     *
     * @param percent [0-1]
     * @return
     */
    public static int getScreenHeightPercent(float percent)
    {
        return (int) ((float) getScreenHeight() * percent);
    }

    /**
     * 获得状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight()
    {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = getResources().getDimensionPixelSize(resourceId);
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
        if (context instanceof Activity)
        {
            if (isStatusBarVisible((Activity) context))
            {
                return getStatusBarHeight();
            } else
            {
                return 0;
            }
        } else
        {
            return getStatusBarHeight();
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
    public static int getIdentifierForDrawable(String drawableName)
    {
        try
        {
            return getResources().getIdentifier(drawableName, "drawable",
                    get().getPackageName());
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
