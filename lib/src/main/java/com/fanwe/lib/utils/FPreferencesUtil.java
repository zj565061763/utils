package com.fanwe.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences工具类
 */
public final class FPreferencesUtil
{
    private FPreferencesUtil()
    {
    }

    public static SharedPreferences getDefaultSharedPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    // put
    public static void putBoolean(String key, boolean value, Context context)
    {
        getDefaultSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    public static void putFloat(String key, float value, Context context)
    {
        getDefaultSharedPreferences(context).edit().putFloat(key, value).commit();
    }

    public static void putInt(String key, int value, Context context)
    {
        getDefaultSharedPreferences(context).edit().putInt(key, value).commit();
    }

    public static void putLong(String key, long value, Context context)
    {
        getDefaultSharedPreferences(context).edit().putLong(key, value).commit();
    }

    public static void putString(String key, String value, Context context)
    {
        getDefaultSharedPreferences(context).edit().putString(key, value).commit();
    }

    public static void putDouble(String key, double value, Context context)
    {
        putString(key, String.valueOf(value), context);
    }

    // get
    public static String getString(String key, String defaultValue, Context context)
    {
        return getDefaultSharedPreferences(context).getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue, Context context)
    {
        return getDefaultSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue, Context context)
    {
        return getDefaultSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue, Context context)
    {
        return getDefaultSharedPreferences(context).getLong(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue, Context context)
    {
        return getDefaultSharedPreferences(context).getFloat(key, defaultValue);
    }

    public static double getDouble(String key, double defaultValue, Context context)
    {
        try
        {
            return Double.valueOf(getString(key, "", context));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void remove(String key, Context context)
    {
        getDefaultSharedPreferences(context).edit().remove(key).commit();
    }

    public static void clear(Context context)
    {
        getDefaultSharedPreferences(context).edit().clear().commit();
    }
}
