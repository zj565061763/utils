package com.fanwe.lib.utils;

import android.text.TextUtils;

import java.net.InetAddress;
import java.net.URL;

/**
 * 网络工具类
 */
public final class FNetUtil
{
    private FNetUtil()
    {
    }

    /**
     * 返回url的IP地址
     *
     * @param url
     * @return
     */
    public static String getHostAddress(String url)
    {
        try
        {
            final String host = new URL(url).getHost();
            if (TextUtils.isEmpty(host))
            {
                return null;
            }
            return InetAddress.getByName(host).getHostAddress();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHost(String url)
    {
        try
        {
            return new URL(url).getHost();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
