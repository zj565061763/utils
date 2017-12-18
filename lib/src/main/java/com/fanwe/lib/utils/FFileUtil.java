package com.fanwe.lib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

public class FFileUtil
{
    /**
     * sd卡是否存在
     *
     * @return
     */
    public static boolean isSdcardExist()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获得sd卡根目录file对象引用
     *
     * @return
     */
    public static File getSdCardFile()
    {
        if (isSdcardExist())
        {
            return Environment.getExternalStorageDirectory();
        } else
        {
            return null;
        }
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
        if (isSdcardExist())
        {
            dir = new File(context.getExternalCacheDir(), dirName);
        } else
        {
            dir = new File(context.getCacheDir(), dirName);
        }
        if (dir.exists() || dir.mkdirs())
        {
            return dir;
        } else
        {
            return null;
        }
    }

    /**
     * 获得公共的相册目录
     *
     * @return
     */
    public static File getPicturesDir()
    {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if ((dir != null) && (dir.exists() || dir.mkdirs()))
        {
            return dir;
        } else
        {
            return null;
        }
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
        } else if (fileLength < 1024)
        {
            fileSizeString = df.format((double) fileLength) + "B";
        } else if (fileLength < 1048576)
        {
            fileSizeString = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824)
        {
            fileSizeString = df.format((double) fileLength / 1048576) + "MB";
        } else
        {
            fileSizeString = df.format((double) fileLength / 1073741824) + "GB";
        }
        return fileSizeString;
    }

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
        File file = new File(dir, String.valueOf(current + ext));
        while (file.exists())
        {
            current++;
            file = new File(dir, String.valueOf(current + ext));
        }
        return file;
    }

    public static File getAnrFile(Context context)
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
