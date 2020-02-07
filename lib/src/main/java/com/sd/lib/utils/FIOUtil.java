package com.sd.lib.utils;

import android.database.Cursor;
import android.text.TextUtils;

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
        } catch (IOException e)
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
        if (file == null)
            return false;

        final File fileParent = file.getParentFile();
        if (fileParent != null && !fileParent.exists())
        {
            if (!fileParent.mkdirs())
                return false;
        }

        if (content == null)
            content = "";

        OutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(file);
            writeString(outputStream, content, null);
            return true;
        } catch (IOException e)
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
        if (file == null || TextUtils.isEmpty(content))
            return false;

        final File fileParent = file.getParentFile();
        if (fileParent != null && !fileParent.exists())
        {
            if (!fileParent.mkdirs())
                return false;
        }

        OutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(file, true);
            writeString(outputStream, content, null);
            return true;
        } catch (IOException e)
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
    public static boolean copyFile(File fileFrom, File fileTo)
    {
        if (fileFrom == null || !fileFrom.exists())
            return false;

        if (fileFrom.isDirectory())
            throw new IllegalArgumentException("fileFrom must not be a directory");

        if (fileTo == null)
            return false;

        if (fileTo.exists())
        {
            if (fileTo.isDirectory())
            {
                throw new IllegalArgumentException("fileTo must not be a directory");
            } else
            {
                if (!fileTo.delete())
                    return false;
            }
        }

        final File fileToParent = fileTo.getParentFile();
        if (fileToParent != null && !fileToParent.exists())
        {
            if (!fileToParent.mkdirs())
                return false;
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try
        {
            inputStream = new FileInputStream(fileFrom);
            outputStream = new FileOutputStream(fileTo);

            copy(inputStream, outputStream);
            return true;
        } catch (IOException e)
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
     * 拷贝文件或者到指定文件夹下的所有文件到目标文件夹
     *
     * @param src 源文件或者文件夹
     * @param dir 目标文件夹
     * @return
     */
    public static boolean copyToDir(File src, File dir)
    {
        if (src == null || !src.exists())
            return false;

        if (dir == null)
            return false;

        if (dir.exists())
        {
            if (dir.isFile())
                throw new IllegalArgumentException("dir must be a directory");
        } else
        {
            if (!dir.mkdirs())
                return false;
        }

        if (src.isFile())
        {
            return copyFile(src, new File(dir, src.getName()));
        } else
        {
            final File[] files = src.listFiles();
            if (files != null && files.length > 0)
            {
                for (File item : files)
                {
                    final File fileTarget = new File(dir, item.getName());

                    boolean result = false;
                    if (item.isFile())
                        result = copyFile(item, fileTarget);
                    else
                        result = copyToDir(item, fileTarget);

                    if (!result)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * 解压
     *
     * @param zip 压缩包文件
     * @param dir 目标文件夹
     * @return
     */
    public static boolean unzip(File zip, File dir)
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
    public static boolean unzip(InputStream is, File dir)
    {
        if (is == null || dir == null)
            return false;

        if (dir.exists())
        {
            if (dir.isFile())
                throw new IllegalArgumentException("dir must be a directory");
        }

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
                    if (file.exists() && file.isFile())
                    {
                        if (!file.delete())
                            return false;
                    }

                    if (!file.exists() && !file.mkdirs())
                        return false;
                } else
                {
                    final File parentFile = file.getParentFile();
                    if (parentFile != null && !parentFile.exists())
                    {
                        if (!parentFile.mkdirs())
                            return false;
                    }

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
     * @param zip
     * @return
     */
    public static boolean zip(File src, File zip)
    {
        if (src == null || !src.exists())
            return false;

        final File[] files = new File[]{src};
        return zip(files, zip);
    }

    /**
     * 压缩文件
     *
     * @param files
     * @param zip
     * @return
     */
    public static boolean zip(File[] files, File zip)
    {
        if (files == null || files.length <= 0)
            return false;

        if (zip == null)
            return false;

        if (zip.exists())
        {
            if (zip.isDirectory())
            {
                throw new IllegalArgumentException("zip must not be a directory");
            } else
            {
                if (!zip.delete())
                    return false;
            }
        }

        final File fileParent = zip.getParentFile();
        if (fileParent != null && !fileParent.exists())
        {
            if (!fileParent.mkdirs())
                return false;
        }

        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;

        try
        {
            fileOutputStream = new FileOutputStream(zip);
            zipOutputStream = new ZipOutputStream(fileOutputStream);

            for (File item : files)
            {
                if (item == null || !item.exists())
                    return false;

                compressFile(item, item.getName(), zipOutputStream);
            }
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
