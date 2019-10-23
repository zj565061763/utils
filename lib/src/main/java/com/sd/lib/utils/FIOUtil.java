package com.sd.lib.utils;

import android.database.Cursor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FIOUtil
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
            inputStream = new BufferedInputStream(inputStream);

        ByteArrayOutputStream out = null;
        try
        {
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1)
            {
                out.write(buffer, 0, len);
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
     * @param charset     编码格式，如果为空则默认编码为UTF-8
     * @return
     * @throws IOException
     */
    public static String readString(InputStream inputStream, Charset charset) throws IOException
    {
        if (charset == null)
            charset = Charset.defaultCharset();

        if (!(inputStream instanceof BufferedInputStream))
            inputStream = new BufferedInputStream(inputStream);

        final Reader reader = new InputStreamReader(inputStream, charset);
        final StringBuilder sb = new StringBuilder();
        final char[] buffer = new char[1024];

        int len = -1;
        while ((len = reader.read(buffer)) != -1)
        {
            sb.append(buffer, 0, len);
        }
        return sb.toString();
    }

    /**
     * 往输出流写入字符串
     *
     * @param outputStream 输出流
     * @param content      字符串内容
     * @param charset      编码格式，如果为空则默认编码为UTF-8
     * @throws IOException
     */
    public static void writeString(OutputStream outputStream, String content, Charset charset) throws IOException
    {
        if (charset == null)
            charset = Charset.defaultCharset();

        final Writer writer = new OutputStreamWriter(outputStream, charset);
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
            inputStream = new BufferedInputStream(inputStream);

        if (!(outputStream instanceof BufferedOutputStream))
            outputStream = new BufferedOutputStream(outputStream);

        final byte[] buffer = new byte[1024];
        int len = 0;
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

    //---------- util method start ----------

    /**
     * 从文件中读取字符串
     *
     * @param file
     * @return
     */
    public static String readStringFromFile(File file)
    {
        if (file == null || !file.exists())
            return null;

        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
            return readString(inputStream, null);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            closeQuietly(inputStream);
        }
    }

    /**
     * 写入字符串到文件中
     *
     * @param content
     * @param file
     * @return
     */
    public static boolean writeStringToFile(String content, File file)
    {
        if (file == null || content == null)
            return false;

        OutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(file);
            writeString(outputStream, content, null);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            closeQuietly(outputStream);
        }
    }

    /**
     * 添加字符串到文件的末尾
     *
     * @param content
     * @param file
     * @return
     */
    public static boolean appendStringToFile(String content, File file)
    {
        if (file == null || content == null)
            return false;

        OutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(file, true);
            writeString(outputStream, content, null);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            closeQuietly(outputStream);
        }
    }

    /**
     * 拷贝文件
     *
     * @param fileFrom
     * @param fileTo
     * @return
     */
    public static boolean copy(File fileFrom, File fileTo)
    {
        if (fileFrom == null || !fileFrom.exists())
            return false;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try
        {
            if (!fileTo.exists())
                fileTo.createNewFile();

            inputStream = new FileInputStream(fileFrom);
            outputStream = new FileOutputStream(fileTo);

            copy(inputStream, outputStream);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        } finally
        {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }
    }

    /**
     * 解压
     *
     * @param zip 压缩包文件
     * @param dir 目标文件夹
     * @return
     */
    private static boolean unzip(File zip, File dir)
    {
        try
        {
            return unzip(new FileInputStream(zip), dir);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解压
     *
     * @param is  压缩包输入流
     * @param dir 目标文件夹
     * @return
     */
    private static boolean unzip(InputStream is, File dir)
    {
        ZipInputStream zipInputStream = null;
        FileOutputStream fileOutputStream = null;

        try
        {
            zipInputStream = new ZipInputStream(is);

            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null)
            {
                final String name = zipEntry.getName();
                final File file = new File(dir, name);

                if (zipEntry.isDirectory())
                {
                    file.mkdirs();
                } else
                {
                    fileOutputStream = new FileOutputStream(file);
                    copy(zipInputStream, fileOutputStream);
                    fileOutputStream.close();
                }

                zipInputStream.closeEntry();
            }

            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            FIOUtil.closeQuietly(zipInputStream);
            FIOUtil.closeQuietly(fileOutputStream);
        }
        return false;
    }

    /**
     * 压缩文件
     *
     * @param src
     * @param to
     * @return
     */
    public static boolean zip(File src, File to)
    {
        if (src == null || !src.exists())
            return false;

        if (to == null)
            return false;

        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream(to);
            zipOutputStream = new ZipOutputStream(fileOutputStream);

            compressFile(src, src.getName(), zipOutputStream);
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            closeQuietly(zipOutputStream);
            closeQuietly(fileOutputStream);
        }
        return false;
    }

    private static void compressFile(File file, String filename, ZipOutputStream outputStream) throws IOException
    {
        if (file.isDirectory())
        {
            outputStream.putNextEntry(new ZipEntry(filename + File.separator));

            final File[] files = file.listFiles();
            if (file != null)
            {
                for (File item : files)
                {
                    compressFile(item, filename + File.separator + item.getName(), outputStream);
                }
            }
        } else
        {
            outputStream.putNextEntry(new ZipEntry(filename));

            FileInputStream fileInputStream = null;
            try
            {
                fileInputStream = new FileInputStream(file);
                copy(fileInputStream, outputStream);
            } finally
            {
                closeQuietly(fileInputStream);
            }
        }
    }

    //---------- util method end ----------
}
