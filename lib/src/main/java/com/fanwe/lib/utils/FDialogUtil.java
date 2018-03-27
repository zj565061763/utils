package com.fanwe.lib.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

public class FDialogUtil
{
    private FDialogUtil()
    {
    }

    //----------dialog position start----------

    /**
     * 设置dialog在view的顶部和view左边对齐
     *
     * @param dialog
     * @param view
     * @param marginBottom
     * @param marginLeft
     */
    public static void setDialogTopAlignLeft(Dialog dialog, View view, int marginBottom, int marginLeft)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.BOTTOM | Gravity.LEFT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] + marginLeft;
            int y = getScreenHeight(context) - location[1] + marginBottom;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的顶部和view中心对齐
     *
     * @param dialog
     * @param view
     * @param marginBottom
     * @param marginLeft
     */
    public static void setDialogTopAlignCenter(Dialog dialog, View view, int marginBottom, int marginLeft)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] - getScreenWidth(context) / 2 + view.getWidth() / 2 + marginLeft;
            int y = getScreenHeight(context) - location[1] + marginBottom;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的顶部和view右边对齐
     *
     * @param dialog
     * @param view
     * @param marginBottom
     * @param marginRight
     */
    public static void setDialogTopAlignRight(Dialog dialog, View view, int marginBottom, int marginRight)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = getScreenWidth(context) - location[0] - view.getWidth() + marginRight;
            int y = getScreenHeight(context) - location[1] + marginBottom;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的底部和view左边对齐
     *
     * @param dialog
     * @param view
     * @param marginTop
     * @param marginLeft
     */
    public static void setDialogBottomAlignLeft(Dialog dialog, View view, int marginTop, int marginLeft)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.TOP | Gravity.LEFT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] + marginLeft;
            int y = location[1] + view.getHeight() + marginTop - getActivityStatusBarHeight(context);

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的底部和view中心对齐
     *
     * @param dialog
     * @param view
     * @param marginTop
     * @param marginLeft
     */
    public static void setDialogBottomAlignCenter(Dialog dialog, View view, int marginTop, int marginLeft)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] - getScreenWidth(context) / 2 + view.getWidth() / 2 + marginLeft;
            int y = location[1] + view.getHeight() + marginTop - getActivityStatusBarHeight(context);

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的底部和view右边对齐
     *
     * @param dialog
     * @param view
     * @param marginTop
     * @param marginRight
     */
    public static void setDialogBottomAlignRight(Dialog dialog, View view, int marginTop, int marginRight)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.TOP | Gravity.RIGHT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = getScreenWidth(context) - location[0] - view.getWidth() + marginRight;
            int y = location[1] + view.getHeight() + marginTop - getActivityStatusBarHeight(context);

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的左边和view顶部对齐
     *
     * @param dialog
     * @param view
     * @param marginRight
     * @param marginTop
     */
    public static void setDialogLeftAlignTop(Dialog dialog, View view, int marginRight, int marginTop)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.TOP | Gravity.RIGHT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = getScreenWidth(context) - location[0] + marginRight;
            int y = location[1] + marginTop - getActivityStatusBarHeight(context);

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的左边和view中心对齐
     *
     * @param dialog
     * @param view
     * @param marginRight
     * @param marginTop
     */
    public static void setDialogLeftAlignCenter(Dialog dialog, View view, int marginRight, int marginTop)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = getScreenWidth(context) - location[0] + marginRight;
            int y = location[1] - getScreenHeight(context) / 2 + view.getHeight() / 2 - getActivityStatusBarHeight(context) / 2 + marginTop;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的左边和view底部对齐
     *
     * @param dialog
     * @param view
     * @param marginRight
     * @param marginBottom
     */
    public static void setDialogLeftAlignBottom(Dialog dialog, View view, int marginRight, int marginBottom)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = getScreenWidth(context) - location[0] + marginRight;
            int y = getScreenHeight(context) - location[1] - view.getHeight() + marginBottom;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的右边和view顶部对齐
     *
     * @param dialog
     * @param view
     * @param marginLeft
     * @param marginTop
     */
    public static void setDialogRightAlignTop(Dialog dialog, View view, int marginLeft, int marginTop)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.TOP | Gravity.LEFT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] + view.getWidth() + marginLeft;
            int y = location[1] + marginTop - getActivityStatusBarHeight(context);

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的右边和view中心对齐
     *
     * @param dialog
     * @param view
     * @param marginLeft
     * @param marginTop
     */
    public static void setDialogRightAlignCenter(Dialog dialog, View view, int marginLeft, int marginTop)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] + view.getWidth() + marginLeft;
            int y = location[1] - getScreenHeight(context) / 2 + view.getHeight() / 2 - getActivityStatusBarHeight(context) / 2 + marginTop;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    /**
     * 设置dialog在view的右边和view底部对齐
     *
     * @param dialog
     * @param view
     * @param marginLeft
     * @param marginBottom
     */
    public static void setDialogRightAlignBottom(Dialog dialog, View view, int marginLeft, int marginBottom)
    {
        if (dialog == null || view == null)
        {
            return;
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (params != null)
        {
            params.gravity = Gravity.BOTTOM | Gravity.LEFT;
            int[] location = FViewUtil.getLocationOnScreen(view, null);
            Context context = view.getContext();

            int x = location[0] + view.getWidth() + marginLeft;
            int y = getScreenHeight(context) - location[1] - view.getHeight() + marginBottom;

            params.x = x;
            params.y = y;
            dialog.getWindow().setAttributes(params);
        }
    }

    //----------dialog position end----------

    private static int getScreenWidth(Context context)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    private static int getScreenHeight(Context context)
    {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    private static int getActivityStatusBarHeight(Context context)
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

    private static int getStatusBarHeight(Context context)
    {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
        {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static boolean isStatusBarVisible(Activity activity)
    {
        if (activity == null)
        {
            return false;
        }
        return ((activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == 0);
    }
}
