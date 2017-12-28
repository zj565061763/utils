package com.fanwe.lib.utils;

public final class FPrimitiveUtil
{
    private FPrimitiveUtil()
    {
    }

    public static int getInt(String content, int defaultValue)
    {
        try
        {
            return Integer.parseInt(content);
        } catch (Exception e)
        {
            return defaultValue;
        }
    }

    public static long getLong(String content, long defaultValue)
    {
        try
        {
            return Long.parseLong(content);
        } catch (Exception e)
        {
            return defaultValue;
        }
    }

    public static float getFloat(String content, float defaultValue)
    {
        try
        {
            return Float.parseFloat(content);
        } catch (Exception e)
        {
            return defaultValue;
        }
    }

    public static double getDouble(String content, double defaultValue)
    {
        try
        {
            return Double.parseDouble(content);
        } catch (Exception e)
        {
            return defaultValue;
        }
    }
}
