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
     * 返回手机上是否安装了某个app
     *
     * @param packageName
     * @param context
     * @return
     */
    public static boolean isPackageExist(String packageName, Context context)
    {
        PackageManager manager = context.getPackageManager();
        List<PackageInfo> listInfo = manager.getInstalledPackages(0);
        for (PackageInfo item : listInfo)
        {
            if (item.packageName.equalsIgnoreCase(packageName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回某个apk文件的包信息
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
     * 返回当前app的包信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context)
    {
        PackageInfo apkInfo = null;
        try
        {
            PackageManager pm = context.getPackageManager();
            apkInfo = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e)
        {
        }
        return apkInfo;
    }

    /**
     * 安装某个apk
     *
     * @param path    apk文件路径
     * @param context
     * @return
     */
    public static boolean installApkPackage(String path, Context context)
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
     * 返回当前app的MetaData
     *
     * @param context
     * @return
     */
    public static Bundle getMetaData(Context context)
    {
        try
        {
            PackageManager pm = context.getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
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
     * @param packageName app的包名
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
     * 当前app是否处于后台
     *
     * @param context
     * @return
     */
    public static boolean isBackground(Context context)
    {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        final String packageName = context.getPackageName();
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

    public static boolean isAppInstalled(Context context, String packagename)
    {
        try
        {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
            return packageInfo != null;
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     * 获得进程名称
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

    /**
     * 是否是主进程
     *
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context)
    {
        return context.getPackageName().equals(getProcessName(context));
    }

}
