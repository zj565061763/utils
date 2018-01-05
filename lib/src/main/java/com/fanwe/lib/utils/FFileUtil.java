package com.fanwe.lib.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

public final class FFileUtil
{
    public static final long KB = 1024;
    public static final long MB = 1024 * KB;
    public static final long GB = 1024 * MB;

    private FFileUtil()
    {
    }

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
        if (mkdirs(dir))
        {
            return dir;
        } else
        {
            return null;
        }
    }

    /**
     * 返回公共的图片目录(Pictures)
     *
     * @return
     */
    public static File getPicturesDir()
    {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (mkdirs(dir))
        {
            return dir;
        } else
        {
            return null;
        }
    }

    /**
     * 返回目录(DCIM)
     *
     * @return
     */
    public static File getDcimDir()
    {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (mkdirs(dir))
        {
            return dir;
        } else
        {
            return null;
        }
    }

    /**
     * 创建文件夹
     *
     * @param dir
     * @return
     */
    public static boolean mkdirs(File dir)
    {
        if (dir == null)
        {
            return false;
        }
        if (dir.exists())
        {
            return true;
        }
        try
        {
            return dir.mkdirs();
        } catch (Exception e)
        {
            return false;
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
        File file = new File(dir, String.valueOf(current + ext));
        while (file.exists())
        {
            current++;
            file = new File(dir, String.valueOf(current + ext));
        }
        return file;
    }

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
