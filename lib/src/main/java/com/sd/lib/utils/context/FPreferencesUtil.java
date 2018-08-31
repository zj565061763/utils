package com.sd.lib.utils.context;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences工具类
 */
public class FPreferencesUtil extends FContext
{
    private FPreferencesUtil()
    {
    }

    private static SharedPreferences getDefaultSharedPreferences()
    {
        return PreferenceManager.getDefaultSharedPreferences(get());
    }

    // put
    public static void putBoolean(String key, boolean value)
    {
        getDefaultSharedPreferences().edit().putBoolean(key, value).commit();
    }

    public static void putFloat(String key, float value)
    {
        getDefaultSharedPreferences().edit().putFloat(key, value).commit();
    }

    public static void putInt(String key, int value)
    {
        getDefaultSharedPreferences().edit().putInt(key, value).commit();
    }

    public static void putLong(String key, long value)
    {
        getDefaultSharedPreferences().edit().putLong(key, value).commit();
    }

    public static void putString(String key, String value)
    {
        getDefaultSharedPreferences().edit().putString(key, value).commit();
    }

    public static void putDouble(String key, double value)
    {
        putString(key, String.valueOf(value));
    }

    // get
    public static String getString(String key, String defaultValue)
    {
        return getDefaultSharedPreferences().getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue)
    {
        return getDefaultSharedPreferences().getInt(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue)
    {
        return getDefaultSharedPreferences().getBoolean(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue)
    {
        return getDefaultSharedPreferences().getLong(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue)
    {
        return getDefaultSharedPreferences().getFloat(key, defaultValue);
    }

    public static double getDouble(String key, double defaultValue)
    {
        try
        {
            return Double.valueOf(getString(key, ""));
        } catch (Exception e)
        {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static void remove(String key)
    {
        getDefaultSharedPreferences().edit().remove(key).commit();
    }

    public static void clear()
    {
        getDefaultSharedPreferences().edit().clear().commit();
    }
}
