package com.sd.lib.utils.context;

import android.content.Context;

/**
 * 保存Context对象
 */
public class FContext
{
    private static Context sContext;

    protected FContext()
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
