package com.sd.lib.utils.encrypt;

import java.security.MessageDigest;

public class FShaUtils
{
    private FShaUtils()
    {
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String SHA1(String content)
    {
        try
        {
            final MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(content.getBytes());
            final byte[] bytes = messageDigest.digest();

            final int length = bytes.length;
            final StringBuilder builder = new StringBuilder(2 * length);
            for (int i = 0; i < length; i++)
            {
                builder.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
                builder.append(HEX_DIGITS[bytes[i] & 0x0f]);
            }
            return builder.toString();
        } catch (Exception e)
        {
            return content;
        }
    }
}
