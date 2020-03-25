package com.sd.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 * 软键盘操作工具类
 */
public class FKeyboardUtil
{
    private FKeyboardUtil()
    {
    }

    /**
     * {@link #show(View, int)}
     *
     * @param view
     */
    public static void show(View view)
    {
        show(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 显示软键盘
     * <p>
     * {@link InputMethodManager#SHOW_FORCED}<br>
     * {@link InputMethodManager#SHOW_IMPLICIT}
     *
     * @param view
     * @param flag
     */
    public static void show(View view, int flag)
    {
        if (view == null)
            return;

        final Context context = view.getContext();
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        view.setFocusable(true);
        view.requestFocus();
        manager.showSoftInput(view, flag);
    }

    /**
     * {@link #hide(View, int)}
     *
     * @param view
     */
    public static void hide(View view)
    {
        hide(view, 0);
    }

    /**
     * 隐藏软键盘
     * <p>
     * {@link InputMethodManager#HIDE_IMPLICIT_ONLY}
     * {@link InputMethodManager#HIDE_NOT_ALWAYS}
     *
     * @param view
     * @param flag
     */
    public static void hide(View view, int flag)
    {
        if (view == null)
            return;

        final Context context = view.getContext();
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive())
            manager.hideSoftInputFromWindow(view.getWindowToken(), flag);
    }

    /**
     * {@link #hide(Activity, int)}
     *
     * @param activity
     */
    public static void hide(Activity activity)
    {
        hide(activity, 0);
    }

    /**
     * 隐藏软键盘
     * <p>
     * {@link InputMethodManager#HIDE_IMPLICIT_ONLY}
     * {@link InputMethodManager#HIDE_NOT_ALWAYS}
     *
     * @param activity
     * @param flag
     */
    public static void hide(Activity activity, int flag)
    {
        if (activity == null)
            return;

        final InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!manager.isActive())
            return;

        final FrameLayout frameLayout = activity.findViewById(android.R.id.content);
        final EditText editText = new EditText(activity);
        frameLayout.addView(editText, 1, 1);

        show(editText);
        if (manager.isActive(editText))
            manager.hideSoftInputFromWindow(editText.getWindowToken(), flag);

        frameLayout.removeView(editText);
    }

    /**
     * 软键盘是否处于显示状态
     *
     * @param context
     * @return
     */
    public static boolean isActive(Context context)
    {
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return manager.isActive();
    }

    @Deprecated
    public static void showKeyboard(View view)
    {
        show(view);
    }

    @Deprecated
    public static void hideKeyboard(View view)
    {
        hide(view);
    }

    @Deprecated
    public static boolean isKeyboardActive(Context context)
    {
        return isActive(context);
    }
}
