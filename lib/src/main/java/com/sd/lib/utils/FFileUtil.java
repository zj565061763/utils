package com.sd.lib.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FFileUtil
{
    public static final long KB = 1024;
    public static final long MB = 1024 * KB;
    public static final long GB = 1024 * MB;

    private static final List<String> PUBLIC_DIRECTORIES = new ArrayList<>();

    static
    {
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_MUSIC);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_PODCASTS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_RINGTONES);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_ALARMS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_NOTIFICATIONS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_PICTURES);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_MOVIES);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DOWNLOADS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DCIM);

        if (Build.VERSION.SDK_INT >= 19)
            PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DOCUMENTS);
    }

    private FFileUtil()
    {
    }

    /**
     * 外部存储是否存在
     *
     * @return
     */
    public static boolean isExternalStorageMounted()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 返回外部存储指定名称的目录<br>
     * 如果名称为空并且外部存储存在，则返回外部存储目录
     *
     * @param directory 目录名称
     * @return
     */
    public static File getExternalDirectory(String directory)
    {
        if (!isExternalStorageMounted())
            return null;

        if (TextUtils.isEmpty(directory))
            return Environment.getExternalStorageDirectory();

        File dir = null;
        if (PUBLIC_DIRECTORIES.contains(directory))
        {
            dir = Environment.getExternalStoragePublicDirectory(directory);
        } else
        {
            dir = new File(Environment.getExternalStorageDirectory(), directory);
        }

        return mkdirs(dir);
    }

    /**
     * 获得cache下的目录
     *
     * @param dirName 缓存目录下的文件夹名字
     * @param context
     * @return
     */
    public static File getCacheDir(String dirName, Context context)
    {
        File dir = null;
        if (isExternalStorageMounted())
        {
            dir = new File(context.getExternalCacheDir(), dirName);
        } else
        {
            dir = new File(context.getCacheDir(), dirName);
        }
        return mkdirs(dir);
    }

    /**
     * 返回files下的目录
     *
     * @param dirName
     * @param context
     * @return
     */
    public static File getFilesDir(String dirName, Context context)
    {
        File dir = context.getExternalFilesDir(dirName);
        if (checkDir(dir))
            return dir;

        dir = new File(context.getFilesDir(), dirName);
        return mkdirs(dir);
    }

    /**
     * 在文件夹下创建一个文件
     *
     * @param dir 文件夹
     * @param ext 文件扩展名
     * @return 创建的文件
     */
    public static File newFileUnderDir(File dir, String ext)
    {
        if (dir == null)
            return null;

        if (TextUtils.isEmpty(ext))
        {
            ext = "";
        } else
        {
            if (!ext.startsWith("."))
                ext = "." + ext;
        }

        long current = System.currentTimeMillis();
        while (true)
        {
            final File file = new File(dir, current + ext);
            if (file.exists())
            {
                current++;
                continue;
            } else
            {
                return file;
            }
        }
    }

    /**
     * 根据源文件，创建一个输出文件
     *
     * @param source
     * @param outputDir 为null的话，和源文件同一级
     * @return
     */
    public static File newOutputFile(File source, File outputDir)
    {
        if (source == null || !source.exists())
            return null;

        if (outputDir == null)
            outputDir = source.getParentFile();

        outputDir = mkdirs(outputDir);
        if (outputDir == null)
            return null;

        final String ext = MimeTypeMap.getFileExtensionFromUrl(source.getAbsolutePath());
        final File outFile = newFileUnderDir(outputDir, ext);
        return outFile;
    }

    /**
     * 获得文件或者文件夹下所有文件的大小
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file)
    {
        if (file == null || !file.exists())
            return 0;

        if (!file.isDirectory())
            return file.length();

        final File[] files = file.listFiles();
        if (files == null || files.length <= 0)
            return 0;

        long length = 0;
        for (File item : files)
        {
            length += getFileSize(item);
        }
        return length;
    }

    /**
     * 删除文件或者目录
     *
     * @param file
     * @return
     */
    public static boolean delete(File file)
    {
        if (file == null || !file.exists())
            return true;

        if (file.isFile())
            return file.delete();

        final File[] files = file.listFiles();
        if (files != null)
        {
            for (File item : files)
            {
                delete(item);
            }
        }
        return file.delete();
    }

    /**
     * 格式化文件大小
     *
     * @param fileLength
     * @return
     */
    public static String formatFileSize(long fileLength)
    {
        final DecimalFormat df = new DecimalFormat("#.00");

        String fileSizeString = "";
        if (fileLength <= 0)
        {
            fileSizeString = "0.00B";
        } else if (fileLength < KB)
        {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < MB)
        {
            fileSizeString = df.format((double) fileLength / KB) + "KB";
        } else if (fileLength < GB)
        {
            fileSizeString = df.format((double) fileLength / MB) + "MB";
        } else
        {
            fileSizeString = df.format((double) fileLength / GB) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 返回anr文件
     *
     * @return
     */
    public static File getAnrFile()
    {
        final File file = new File("/data/anr/traces.txt");
        if (file.exists())
        {
            return file;
        } else
        {
            return null;
        }
    }

    /**
     * 如果文件夹不存在，则尝试创建文件夹
     *
     * @param dir
     * @return
     */
    public static File mkdirs(File dir)
    {
        return checkDir(dir) ? dir : null;
    }

    /**
     * 检查文件夹是否存在，如果不存在则尝试创建
     *
     * @param dir
     * @return
     */
    public static boolean checkDir(File dir)
    {
        if (dir == null)
            return false;

        if (dir.exists())
            return true;

        try
        {
            return dir.mkdirs();
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Deprecated
    public static boolean deleteFileOrDir(File file)
    {
        return delete(file);
    }
}
