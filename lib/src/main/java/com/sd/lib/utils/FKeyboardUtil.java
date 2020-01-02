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
     * 显示软键盘
     *
     * @param view
     */
    public static void showKeyboard(View view)
    {
        if (view == null)
            return;

        final Context context = view.getContext();
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        view.setFocusable(true);
        view.requestFocus();
        manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideKeyboard(View view)
    {
        if (view == null)
            return;

        final Context context = view.getContext();
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (manager.isActive(view))
        {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } else if (manager.isActive())
        {
            if (context instanceof Activity)
            {
                final Activity activity = (Activity) context;
                final FrameLayout frameLayout = activity.findViewById(android.R.id.content);
                if (frameLayout != null)
                {
                    final EditText editText = new EditText(activity);
                    frameLayout.addView(editText, 1, 1);

                    showKeyboard(editText);
                    if (manager.isActive(editText))
                        manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                    frameLayout.removeView(editText);
                }
            }
        }
    }

    /**
     * 软键盘是否处于显示状态
     *
     * @param context
     * @return
     */
    public static boolean isKeyboardActive(Context context)
    {
        final InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        return manager.isActive();
    }
}
