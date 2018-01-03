package com.fanwe.lib.utils.context;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪贴板工具类
 */
public final class FClipboardUtil extends FContext
{
    private FClipboardUtil()
    {
    }

    /**
     * 设置剪贴板内容
     *
     * @param text
     */
    public static void setText(CharSequence text)
    {
        ClipboardManager clip = (ClipboardManager) get().getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 获得剪贴板内容
     *
     * @return
     */
    public static CharSequence getText()
    {
        ClipboardManager clip = (ClipboardManager) get().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clip.hasPrimaryClip())
        {
            return clip.getPrimaryClip().getItemAt(0).getText();
        } else
        {
            return null;
        }
    }

}
