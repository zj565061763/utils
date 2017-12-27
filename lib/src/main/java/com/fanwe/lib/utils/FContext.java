package com.fanwe.lib.utils;

import android.content.Context;

/**
 * 保存Context对象
 */
public final class FContext
{
    private static Context sContext;

    private FContext()
    {
    }

    public static final void set(Context context)
    {
        if (sContext == null)
        {
            sContext = context.getApplicationContext();
        }
    }

    public static final Context get()
    {
        return sContext;
    }
}
