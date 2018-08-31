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

import java.io.File;
import java.util.List;

public class FPackageUtil extends FContext
{
    private FPackageUtil()
    {
    }

    /**
     * 安装某个apk
     *
     * @param path apk文件路径
     * @return
     */
    public static boolean installApk(String path)
    {
        if (path == null)
        {
            return false;
        }

        if (!new File(path).exists())
        {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        get().startActivity(intent);
        return true;
    }

    /**
     * 返回apk文件的包信息
     *
     * @param apkFilePath
     * @return
     */
    public static PackageInfo getApkPackageInfo(String apkFilePath)
    {
        PackageManager pm = get().getPackageManager();
        PackageInfo apkInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA);
        return apkInfo;
    }

    /**
     * 返回当前app的包信息
     *
     * @return
     */
    public static PackageInfo getPackageInfo()
    {
        return getPackageInfo(get().getPackageName());
    }

    /**
     * 返回app的包信息
     *
     * @param packageName app包名
     * @return
     */
    public static PackageInfo getPackageInfo(String packageName)
    {
        try
        {
            PackageManager pm = get().getPackageManager();
            return pm.getPackageInfo(packageName, 0);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回当前app的MetaData
     *
     * @return
     */
    public static Bundle getAppMetaData()
    {
        return getAppMetaData(get().getPackageName());
    }

    /**
     * 返回app的MetaData
     *
     * @param packageName app包名
     * @return
     */
    public static Bundle getAppMetaData(String packageName)
    {
        try
        {
            PackageManager pm = get().getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return info.metaData;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
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
            PackageManager pm = get().getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage(packageName);
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
        ActivityManager activityManager = (ActivityManager) get().getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        for (RunningAppProcessInfo appProcess : appProcesses)
        {
            if (appProcess.processName.equals(packageName))
            {
                if (appProcess.importance != RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
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
     * 返回手机上是否安装了某个app
     *
     * @param packageName app包名
     * @return
     */
    public static boolean isAppInstalled(String packageName)
    {
        try
        {
            PackageManager pm = get().getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
