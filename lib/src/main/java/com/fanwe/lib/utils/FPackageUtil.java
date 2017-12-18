package com.fanwe.lib.utils;

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
import java.io.IOException;
import java.util.List;

public class FPackageUtil
{

    public static void chmod(String permission, String path)
    {
        try
        {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 返回apk文件的包信息
     *
     * @param apkFilePath
     * @param context
     * @return
     */
    public static PackageInfo getApkPackageInfo(String apkFilePath, Context context)
    {
        PackageManager pm = context.getPackageManager();
        PackageInfo apkInfo = pm.getPackageArchiveInfo(apkFilePath, PackageManager.GET_META_DATA);
        return apkInfo;
    }

    /**
     * 返回app的包信息
     *
     * @param packageName app包名
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(String packageName, Context context)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(packageName, 0);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 安装某个apk
     *
     * @param path    apk文件路径
     * @param context
     * @return
     */
    public static boolean installApk(String path, Context context)
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
        context.startActivity(intent);
        return true;
    }

    /**
     * 返回app的MetaData
     *
     * @param packageName app包名
     * @param context
     * @return
     */
    public static Bundle getAppMetaData(String packageName, Context context)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            return info.metaData;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 启动某个app
     *
     * @param packageName app包名
     * @param context
     * @return
     */
    public static boolean startApp(String packageName, Context context)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            Intent intent = pm.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * app是否处于后台
     *
     * @param packageName app包名
     * @param context
     * @return
     */
    public static boolean isAppBackground(String packageName, Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
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
     * @param context
     * @return
     */
    public static boolean isAppInstalled(String packageName, Context context)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回当前进程的名称
     *
     * @param context
     * @return
     */
    public static String getProcessName(Context context)
    {
        int pid = android.os.Process.myPid();

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null)
        {
            return null;
        }
        for (RunningAppProcessInfo procInfo : runningApps)
        {
            if (procInfo.pid == pid)
            {
                return procInfo.processName;
            }
        }
        return null;
    }
}
