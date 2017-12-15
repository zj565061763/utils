package com.fanwe.lib.utils;

import android.database.Cursor;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public final class FIOUtil
{
    private FIOUtil()
    {
    }

    /**
     * 从输入流中获得byte数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readBytes(InputStream inputStream) throws IOException
    {
        if (!(inputStream instanceof BufferedInputStream))
        {
            inputStream = new BufferedInputStream(inputStream);
        }
        ByteArrayOutputStream out = null;
        try
        {
            out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buf)) != -1)
            {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } finally
        {
            closeQuietly(out);
        }
    }

    /**
     * 从输入流中获得byte数组
     *
     * @param inputStream
     * @param skip
     * @param size
     * @return
     * @throws IOException
     */
    public static byte[] readBytes(InputStream inputStream, long skip, long size) throws IOException
    {
        ByteArrayOutputStream out = null;
        try
        {
            if (skip > 0)
            {
                long skipSize = 0;
                while (skip > 0 && (skipSize = inputStream.skip(skip)) > 0)
                {
                    skip -= skipSize;
                }
            }
            out = new ByteArrayOutputStream();
            for (int i = 0; i < size; i++)
            {
                out.write(inputStream.read());
            }
            return out.toByteArray();
        } finally
        {
            closeQuietly(out);
        }
    }

    /**
     * 从输入流中获得字符串
     *
     * @param inputStream 输入流
     * @return
     * @throws IOException
     */
    public static String readString(InputStream inputStream) throws IOException
    {
        return readString(inputStream, null);
    }

    /**
     * 从输入流中获得字符串
     *
     * @param inputStream 输入流
     * @param charset     编码格式，如果为空则默认编码为UTF-8
     * @return
     * @throws IOException
     */
    public static String readString(InputStream inputStream, String charset) throws IOException
    {
        if (TextUtils.isEmpty(charset))
        {
            charset = FCharset.UTF8;
        }
        if (!(inputStream instanceof BufferedInputStream))
        {
            inputStream = new BufferedInputStream(inputStream);
        }
        Reader reader = new InputStreamReader(inputStream, charset);
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int len = -1;
        while ((len = reader.read(buf)) != -1)
        {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /**
     * 往输出流写入字符串
     *
     * @param outputStream 输出流
     * @param content      字符串内容
     * @throws IOException
     */
    public static void writeString(OutputStream outputStream, String content) throws IOException
    {
        writeString(outputStream, content, null);
    }

    /**
     * 往输出流写入字符串
     *
     * @param outputStream 输出流
     * @param content      字符串内容
     * @param charset      编码格式，如果为空则默认编码为UTF-8
     * @throws IOException
     */
    public static void writeString(OutputStream outputStream, String content, String charset) throws IOException
    {
        if (TextUtils.isEmpty(charset))
        {
            charset = FCharset.UTF8;
        }
        Writer writer = new OutputStreamWriter(outputStream, charset);
        writer.write(content);
        writer.flush();
    }

    /**
     * 将输入流的内容拷贝到输出流
     *
     * @param inputStream  输入流
     * @param outputStream 输出流
     * @throws IOException
     */
    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException
    {
        if (!(inputStream instanceof BufferedInputStream))
        {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(outputStream instanceof BufferedOutputStream))
        {
            outputStream = new BufferedOutputStream(outputStream);
        }
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1)
        {
            outputStream.write(buffer, 0, len);
        }
        outputStream.flush();
    }

    public static void closeQuietly(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            } catch (Throwable ignored)
            {
            }
        }
    }

    public static void closeQuietly(Cursor cursor)
    {
        if (cursor != null)
        {
            try
            {
                cursor.close();
            } catch (Throwable ignored)
            {
            }
        }
    }
}
