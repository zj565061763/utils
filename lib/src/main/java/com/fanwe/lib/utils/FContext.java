package com.fanwe.lib.utils;

import android.content.Context;
import android.content.res.Resources;

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

    public static final Resources getResources()
    {
        return get().getResources();
    }
}
