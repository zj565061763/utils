package com.sd.lib.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Handler管理类
 */
public class FHandlerManager
{
    private static final HandlerThread HANDLER_THREAD = new HandlerThread("HandlerThread");

    static
    {
        HANDLER_THREAD.start();
    }

    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());
    private static final Handler BACKGROUND_HANDLER = new Handler(HANDLER_THREAD.getLooper());

    private FHandlerManager()
    {
    }

    /**
     * 获得主线程Handler
     *
     * @return
     */
    public final static Handler getMainHandler()
    {
        return MAIN_HANDLER;
    }

    /**
     * 获得后台线程Handler
     *
     * @return
     */
    public final static Handler getBackgroundHandler()
    {
        return BACKGROUND_HANDLER;
    }

    /**
     * Ui线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable)
    {
        if (runnable == null)
            return;

        if (Looper.myLooper() == Looper.getMainLooper())
            runnable.run();
        else
            MAIN_HANDLER.post(runnable);
    }
}
