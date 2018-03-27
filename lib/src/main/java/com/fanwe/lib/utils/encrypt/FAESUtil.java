package com.fanwe.lib.utils.encrypt;

import android.util.Base64;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * aes加解密工具类
 */
public class FAESUtil
{
    public static final String DEFAULT_KEY = "1400009129000000";

    private FAESUtil()
    {
    }

    public static String encrypt(String content)
    {
        return encrypt(content, DEFAULT_KEY);
    }

    /**
     * 加密
     *
     * @param content 要加密的内容
     * @param key     key（16位）
     * @return
     */
    public static String encrypt(String content, String key)
    {
        byte[] encryptResult = null;
        String result = null;
        try
        {
            byte[] contentBytes = content.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptResult = cipher.doFinal(contentBytes);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (encryptResult != null)
        {
            result = Base64.encodeToString(encryptResult, Base64.DEFAULT);
        }
        return result;
    }

    public static String decrypt(String content)
    {
        return decrypt(content, DEFAULT_KEY);
    }

    /**
     * 解密
     *
     * @param content 要解密的内容
     * @param key     key（16位）
     * @return
     */
    public static String decrypt(String content, String key)
    {
        String result = null;
        byte[] decryptResult = null;
        try
        {
            byte[] contentBytes = Base64.decode(content, 0);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            decryptResult = cipher.doFinal(contentBytes);
            if (decryptResult != null)
            {
                result = new String(decryptResult, Charset.defaultCharset());
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }

}
