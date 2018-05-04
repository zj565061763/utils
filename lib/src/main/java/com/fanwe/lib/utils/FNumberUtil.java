package com.fanwe.lib.utils;

import java.text.NumberFormat;

public class FNumberUtil
{
    private FNumberUtil()
    {
    }

    public static int getInt(String content)
    {
        return getInt(content, 0);
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

    public static long getLong(String content)
    {
        return getLong(content, 0);
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

    public static float getFloat(String content)
    {
        return getFloat(content, 0);
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

    public static double getDouble(String content)
    {
        return getDouble(content, 0);
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

    public static String format(Object object)
    {
        final NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(Integer.MAX_VALUE);
        format.setMinimumFractionDigits(0);
        return format.format(object);
    }
}
