package com.fanwe.lib.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class FMD5Util
{
    private FMD5Util()
    {
    }

    public static String MD5(String value)
    {
        if (value == null)
        {
            return null;
        }
        byte[] byteValue = value.getBytes();
        return MD5(byteValue);
    }

    public static String MD5(byte[] value)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(value);
            byte[] bytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
            {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1)
                {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
