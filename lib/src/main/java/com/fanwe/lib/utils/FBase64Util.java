package com.fanwe.lib.utils;

import android.util.Base64;

public final class FBase64Util
{
    private FBase64Util()
    {
    }

    public static byte[] encodeToByte(String content)
    {
        byte[] result = null;
        try
        {
            result = Base64.encode(content.getBytes(FCharset.UTF8), Base64.DEFAULT);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    public static byte[] encodeToByte(byte[] content)
    {
        return Base64.encode(content, Base64.DEFAULT);
    }

    public static String encodeToString(byte[] content)
    {
        return Base64.encodeToString(content, Base64.DEFAULT);
    }

    public static String encodeToString(String content)
    {
        String result = null;
        try
        {
            result = Base64.encodeToString(content.getBytes(FCharset.UTF8), Base64.DEFAULT);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    public static String decodeToString(String content)
    {
        String result = null;
        try
        {
            byte[] byteResult = Base64.decode(content, Base64.DEFAULT);
            result = new String(byteResult, FCharset.UTF8);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    public static String decodeToString(byte[] content)
    {
        String result = null;
        try
        {
            byte[] byteResult = Base64.decode(content, Base64.DEFAULT);
            result = new String(byteResult, FCharset.UTF8);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    public static byte[] decodeToByte(String content)
    {
        return Base64.decode(content, Base64.DEFAULT);
    }

    public static byte[] decodeToByte(byte[] content)
    {
        return Base64.decode(content, Base64.DEFAULT);
    }

}
