package com.fanwe.lib.utils;

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
            final String hostAddress = InetAddress.getByName(host).getHostAddress();
            return hostAddress;
        } catch (Exception e)
        {
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
            return null;
        }
    }
}
