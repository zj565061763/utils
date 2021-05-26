package com.sd.lib.utils;

import android.content.Context;

import java.io.File;

/**
 * 用https://github.com/zj565061763/io替代
 */
@Deprecated
public class FTempDirectory
{
    private FTempDirectory()
    {
    }

    /**
     * 返回临时目录
     *
     * @return
     */
    private static File getTempDir(Context context)
    {
        final String dirName = "ftemp_dir";
        return FFileUtil.getFilesDir(dirName, context);
    }

    /**
     * 创建临时文件
     *
     * @param ext
     * @return
     */
    public static synchronized File newFile(String ext, Context context)
    {
        final File dir = getTempDir(context);
        if (dir == null)
            return null;

        final File tempFile = FFileUtil.newFileUnderDir(dir, ext);
        return tempFile;
    }

    /**
     * 删除临时文件目录
     *
     * @param context
     */
    public static synchronized void delete(Context context)
    {
        final File dir = getTempDir(context);
        FFileUtil.delete(dir);
    }
}
