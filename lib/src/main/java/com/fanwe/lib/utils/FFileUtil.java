package com.fanwe.lib.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class FFileUtil
{
    public static final long KB = 1024;
    public static final long MB = 1024 * KB;
    public static final long GB = 1024 * MB;

    private static final List<String> PUBLIC_DIRECTORIES = new ArrayList<>();

    static
    {
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DCIM);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_PICTURES);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DOWNLOADS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_DOCUMENTS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_MUSIC);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_PODCASTS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_RINGTONES);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_ALARMS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_NOTIFICATIONS);
        PUBLIC_DIRECTORIES.add(Environment.DIRECTORY_MOVIES);
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
     * 获得缓存目录
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
     * 返回外部存储指定名称的目录<br>
     * 如果名称为空并且外部存储存在，则返回外部存储目录
     *
     * @param directory 目录名称
     * @return
     */
    public static File getExternalDirectory(String directory)
    {
        if (!isExternalStorageMounted())
        {
            return null;
        }
        if (TextUtils.isEmpty(directory))
        {
            return Environment.getExternalStorageDirectory();
        }

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
     * 创建文件夹
     *
     * @param dir
     * @return
     */
    public static File mkdirs(File dir)
    {
        if (dir == null || dir.exists())
        {
            return dir;
        }
        try
        {
            if (dir.mkdirs())
            {
                return dir;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
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
        {
            return null;
        }
        if (ext == null)
        {
            ext = "";
        }

        long current = System.currentTimeMillis();
        File file = new File(dir, current + ext);
        while (file.exists())
        {
            current++;
            file = new File(dir, current + ext);
        }
        return file;
    }

    /**
     * 获得文件或者文件夹下所有文件的大小
     *
     * @param file
     * @return
     */
    public static long getFileOrDirSize(File file)
    {
        if (file == null || !file.exists())
        {
            return 0;
        }
        if (!file.isDirectory())
        {
            return file.length();
        }
        File[] files = file.listFiles();
        if (files == null || files.length <= 0)
        {
            return 0;
        }
        long length = 0;
        for (File item : files)
        {
            length += getFileOrDirSize(item);
        }
        return length;
    }

    /**
     * 删除文件或者目录
     *
     * @param file
     * @return
     */
    public static boolean deleteFileOrDir(File file)
    {
        if (file == null || !file.exists())
        {
            return true;
        }
        if (file.isFile())
        {
            return file.delete();
        }
        File[] files = file.listFiles();
        if (files != null)
        {
            for (File item : files)
            {
                deleteFileOrDir(item);
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
        DecimalFormat df = new DecimalFormat("#.00");
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
        File file = new File("/data/anr/traces.txt");
        if (file.exists())
        {
            return file;
        } else
        {
            return null;
        }
    }
}
