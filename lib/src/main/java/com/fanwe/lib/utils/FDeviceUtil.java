package com.fanwe.lib.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public final class FDeviceUtil
{
    private FDeviceUtil()
    {
    }

    /**
     * 获取设备ID
     */
    public static String getDeviceId(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
