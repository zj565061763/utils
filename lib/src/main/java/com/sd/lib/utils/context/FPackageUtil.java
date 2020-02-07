package com.sd.lib.utils.context;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.sd.lib.context.FContext;

import java.io.File;
import java.util.List;

public class FPackageUtil extends FContext
{
    private FPackageUtil()
    {
    }

    /**
     * 返回当前app的包信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo()
    {
        return getPackageInfo(get().getPackageName(), PackageManager.GET_CONFIGURATIONS);
    }

    /**
     * 返回当前app的包信息
     *
     * @param flag
     * @return
     */
    public static PackageInfo getPackageInfo(int flag)
    {
        return getPackageInfo(get().getPackageName(), flag);
    }

    /**
     * 返回app的包信息
     *
     * @param packageName app包名
     * @param flag
     * @return
     */
    public static PackageInfo getPackageInfo(String packageName, int flag)
    {
        try
        {
            final PackageManager manager = get().getPackageManager();
            final PackageInfo info = manager.getPackageInfo(packageName, flag);
            return info;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回apk文件的包信息
     *
     * @param apkFilePath
     * @param flag
     * @return
     */
    public static PackageInfo getPackageInfoApk(String apkFilePath, int flag)
    {
        final PackageManager manager = get().getPackageManager();
        final PackageInfo info = manager.getPackageArchiveInfo(apkFilePath, flag);
        return info;
    }

    /**
     * 返回当前app的MetaData
     *
     * @return
     */
    public static Bundle getMetaData()
    {
        return getMetaData(get().getPackageName());
    }

    /**
     * 返回app的MetaData
     *
     * @param packageName app包名
     * @return
     */
    public static Bundle getMetaData(String packageName)
    {
        try
        {
            final PackageManager manager = get().getPackageManager();
            final ApplicationInfo info = manager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return info.metaData;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 启动当前app
     *
     * @return
     */
    public static boolean startApp()
    {
        return startApp(get().getPackageName());
    }

    /**
     * 启动某个app
     *
     * @param packageName app包名
     * @return
     */
    public static boolean startApp(String packageName)
    {
        try
        {
            final PackageManager manager = get().getPackageManager();
            final Intent intent = manager.getLaunchIntentForPackage(packageName);
            get().startActivity(intent);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 当前app是否处于后台
     *
     * @return
     */
    public static boolean isAppBackground()
    {
        return isAppBackground(get().getPackageName());
    }

    /**
     * app是否处于后台
     *
     * @param packageName app包名
     * @return
     */
    public static boolean isAppBackground(String packageName)
    {
        final ActivityManager manager = (ActivityManager) get().getSystemService(Context.ACTIVITY_SERVICE);
        final List<RunningAppProcessInfo> listInfo = manager.getRunningAppProcesses();

        for (RunningAppProcessInfo item : listInfo)
        {
            if (item.processName.equals(packageName))
            {
                if (item.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 安装某个apk
     *
     * @param path apk文件路径
     * @return
     */
    public static boolean installApk(String path)
    {
        if (TextUtils.isEmpty(path))
            return false;

        if (!new File(path).exists())
            return false;

        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        get().startActivity(intent);
        return true;
    }

    /**
     * 返回手机上是否安装了某个app
     *
     * @param packageName app包名
     * @return
     */
    public static boolean isAppInstalled(String packageName)
    {
        return getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS) != null;
    }
}
