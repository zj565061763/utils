package com.sd.lib.utils.context;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.sd.lib.context.FContext;

/**
 * toast显示类，可以在子线程直接调用
 */
public class FToast extends FContext
{
    private FToast()
    {
    }

    public static void show(CharSequence text)
    {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(final CharSequence text, final int duration)
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            showInternal(text, duration);
        } else
        {
            new Handler(Looper.getMainLooper()).post(new Runnable()
            {
                @Override
                public void run()
                {
                    showInternal(text, duration);
                }
            });
        }
    }

    private static void showInternal(CharSequence text, int duration)
    {
        if (TextUtils.isEmpty(text))
            return;

        Toast.makeText(get(), text, duration).show();
    }
}
