package com.fanwe.lib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

public final class FIntentUtil
{
    private FIntentUtil()
    {
    }

    /**
     * 选择文件
     *
     * @return
     */
    public static Intent getIntentGetContent()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        return intent;
    }

    /**
     * 用系统浏览器打开url链接
     *
     * @param url
     * @return
     */
    public static Intent getIntentOpenBrowser(String url)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * app设置界面
     *
     * @param packageName app包名
     * @return
     */
    public static Intent getIntentApplicationDetailsSettings(String packageName)
    {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", packageName, null));
        return intent;
    }

    /**
     * 网络设置界面
     *
     * @return
     */
    public static Intent getIntentWirelessSettings()
    {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_WIRELESS_SETTINGS);
        return intent;
    }

    /**
     * 系统图库选择界面
     *
     * @return
     */
    public static Intent getIntentSelectImage()
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        return intent;
    }

    /**
     * 调用系统相机拍照
     *
     * @param saveFile 图片要保存的file
     * @return
     */
    public static Intent getIntentTakePhoto(File saveFile)
    {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(saveFile));
        return intent;
    }

    /**
     * 拨打电话界面
     *
     * @param phoneNumber
     * @return
     */
    public static Intent getIntentCallPhone(String phoneNumber)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        return intent;
    }

    /**
     * qq聊天界面
     *
     * @param qqNumber 对方的qq号码
     * @return
     */
    public static Intent getIntentQQChat(String qqNumber)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber));
        return intent;
    }

    public static Intent getIntentChooser(String title, Intent... intents)
    {
        // 显示一个供用户选择的应用列表
        Intent intent = new Intent(Intent.ACTION_CHOOSER);
        intent.setAction(Intent.ACTION_CHOOSER);
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents);
        if (!TextUtils.isEmpty(title))
        {
            intent.putExtra(Intent.EXTRA_TITLE, title);
        }
        return intent;
    }

    /**
     * 获调发送邮件的intent
     *
     * @return
     */
    public static Intent getIntentSendEmail(String email, String subject)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        return intent;
    }


    public static boolean isIntentAvailable(Intent intent, Context context)
    {
        if (intent == null)
        {
            return false;
        }

        List<ResolveInfo> listInfo = context.getPackageManager()
                .queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);

        return listInfo != null && !listInfo.isEmpty();
    }

    public static Intent getIntentLocalMap(double latitude, double longitude, String name, Context context)
    {
        String uriString = "geo:" + latitude + "," + longitude;
        if (!TextUtils.isEmpty(name))
        {
            uriString = uriString + "?q=" + name;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        if (isIntentAvailable(intent, context))
        {
            return intent;
        }

        String uriPre = "http://ditu.google.cn/maps?hl=zh&mrt=loc&q=";
        uriString = uriPre + latitude + "," + longitude;
        if (!TextUtils.isEmpty(name))
        {
            uriString = uriString + "(" + name + ")";
        }
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
        return intent;
    }
}
