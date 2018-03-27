package com.fanwe.lib.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhengjun on 2018/2/5.
 */
public class FStringUtil
{
    private FStringUtil()
    {
    }

    public static String buildUrl(String baseUrl, Map<String, String> params)
    {
        if (baseUrl == null || baseUrl.isEmpty() ||
                params == null || params.isEmpty())
        {
            return baseUrl;
        }

        StringBuilder sb = new StringBuilder(baseUrl);
        if (!baseUrl.contains("?"))
        {
            sb.append("?");
        } else
        {
            if (!baseUrl.endsWith("&"))
            {
                sb.append("&");
            }
        }

        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String, String> item = it.next();
            sb.append(item.getKey()).append("=").append(item.getValue());
            sb.append('&');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    //---------- Primitive start ----------

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

    //---------- Primitive end ----------
}
