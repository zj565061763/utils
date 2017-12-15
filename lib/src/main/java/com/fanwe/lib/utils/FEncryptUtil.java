package com.fanwe.lib.utils;

import android.util.Base64;

/**
 * Created by zhengjun on 2017/12/15.
 */
public final class FEncryptUtil
{
    private FEncryptUtil()
    {
    }

    public static byte[] base64EncodeToByte(String content)
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

    public static byte[] base64EncodeToByte(byte[] content)
    {
        return Base64.encode(content, Base64.DEFAULT);
    }

    public static String base64EncodeToString(byte[] content)
    {
        return Base64.encodeToString(content, Base64.DEFAULT);
    }

    public static String base64EncodeToString(String content)
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

    public static String base64DecodeToString(String content)
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

    public static String base64DecodeToString(byte[] content)
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
        byte[] result = null;
        try
        {
            result = Base64.decode(content, Base64.DEFAULT);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }

    public static byte[] decodeToByte(byte[] content)
    {
        byte[] result = null;
        try
        {
            result = Base64.decode(content, Base64.DEFAULT);
        } catch (Exception e)
        {
            result = null;
        }
        return result;
    }


}
