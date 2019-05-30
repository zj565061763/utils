package com.sd.lib.utils.context;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.sd.lib.context.FContext;

public class FDeviceUtil extends FContext
{
    private FDeviceUtil()
    {
    }

    /**
     * 获取设备ID
     */
    public static String getDeviceId()
    {
        TelephonyManager tm = (TelephonyManager) get().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
