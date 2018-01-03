package com.fanwe.lib.utils;

import android.text.TextUtils;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

/**
 * 网络工具类
 */
public final class FNetUtil
{
    private FNetUtil()
    {
    }

    /**
     * 域名是否可用，只能在后台线程调用
     *
     * @param host
     * @return
     */
    public static boolean isHostAvailable(String host)
    {
        try
        {
            InetAddress inetAddress = InetAddress.getByName(host);
            String hostAddress = inetAddress.getHostAddress();
            return !TextUtils.isEmpty(hostAddress);
        } catch (UnknownHostException e)
        {
            return false;
        }
    }

    public static String getHost(String url)
    {
        try
        {
            URI uri = new URI(url);
            return uri.getHost();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
