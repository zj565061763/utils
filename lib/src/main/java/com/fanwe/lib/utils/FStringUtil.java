package com.fanwe.lib.utils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhengjun on 2018/2/5.
 */
public final class FStringUtil
{
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
        Map.Entry<String, String> item = it.next();

        sb.append(item.getKey()).append("=").append(item.getValue());
        while (it.hasNext())
        {
            item = it.next();

            sb.append('&');
            sb.append(item.getKey()).append("=").append(item.getValue());
        }

        return sb.toString();
    }
}
