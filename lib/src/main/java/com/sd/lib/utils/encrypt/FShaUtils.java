package com.sd.lib.utils.encrypt;

import java.security.MessageDigest;

public class FShaUtils
{
    private FShaUtils()
    {
    }

    public static String SHA1(String content)
    {
        try
        {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(content.getBytes("utf-8"));
            final byte[] bytes = messageDigest.digest();
            return String.valueOf(FHexUtils.encodeHex(bytes, true));
        } catch (Exception e)
        {
            return content;
        }
    }
}
