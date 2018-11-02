package com.sd.lib.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FMD5Util
{
    private FMD5Util()
    {
    }

    public static String MD5_16(String value)
    {
        return MD5_16(value.getBytes());
    }

    public static String MD5_16(byte[] value)
    {
        return MD5(value).substring(8, 24);
    }

    public static String MD5(String value)
    {
        return MD5(value.getBytes());
    }

    public static String MD5(byte[] value)
    {
        try
        {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(value);
            final byte[] bytes = messageDigest.digest();

            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
            {
                final String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1)
                    sb.append('0');

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
