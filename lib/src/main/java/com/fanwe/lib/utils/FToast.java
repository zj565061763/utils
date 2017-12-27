package com.fanwe.lib.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * toast显示类，可以在子线程直接调用
 */
public final class FToast
{
    private static Toast sToast;

    private FToast()
    {
    }

    public static void show(CharSequence text, Context context)
    {
        show(text, Toast.LENGTH_LONG, context);
    }

    public static void show(final CharSequence text, final int duration, final Context context)
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            showInternal(text, duration, context);
        } else
        {
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    showInternal(text, duration, context);
                }
            });
        }
    }

    private static void showInternal(CharSequence text, int duration, Context context)
    {
        if (TextUtils.isEmpty(text))
        {
            return;
        }
        if (sToast != null)
        {
            sToast.setText(text);
            sToast.setDuration(duration);
        } else
        {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        }
        sToast.show();
    }

}
