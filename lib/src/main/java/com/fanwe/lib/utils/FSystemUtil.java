package com.fanwe.lib.utils;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

public final class FSystemUtil
{
    private FSystemUtil()
    {
    }

    /**
     * 获得系统亮度
     *
     * @param context
     * @return 0-255
     */
    public static int getScreenBrightness(Context context)
    {
        int value = -1;
        try
        {
            value = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 设置系统亮度
     *
     * @param context
     * @param value   0-255
     * @return true-设置成功
     */
    public static boolean setScreenBrightness(Context context, int value)
    {
        return Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, value);
    }

    /**
     * 获得亮度模式
     *
     * @param context
     * @return 0-正常模式，1-自动模式
     */
    public static int getScreenBrightnessMode(Context context)
    {
        int value = -1;
        try
        {
            value = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获得音量
     *
     * @param context
     * @param streamType 音量类型{@link AudioManager}
     * @return
     */
    public static int getStreamVolume(Context context, int streamType)
    {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int value = audioManager.getStreamVolume(streamType);
        return value;
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
