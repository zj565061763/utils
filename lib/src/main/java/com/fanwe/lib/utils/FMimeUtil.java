package com.fanwe.lib.utils;

public class FMimeUtil
{
    private FMimeUtil()
    {
    }

    public static String getMime(String extString)
    {
        String mimeType = null;
        if ("jpg".equalsIgnoreCase(extString))
        {
            mimeType = "image/jpeg";
        } else if ("jpe".equalsIgnoreCase(extString))
        {
            mimeType = "image/jpeg";
        } else if ("jpeg".equalsIgnoreCase(extString))
        {
            mimeType = "image/jpeg";
        } else if ("png".equalsIgnoreCase(extString))
        {
            mimeType = "image/png";
        } else if ("gif".equalsIgnoreCase(extString))
        {
            mimeType = "image/gif";
        } else
        {

        }
        return mimeType;
    }

}
