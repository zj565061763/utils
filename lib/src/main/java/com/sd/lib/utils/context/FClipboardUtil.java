package com.sd.lib.utils.context;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.sd.lib.context.FContext;

/**
 * 剪贴板工具类
 */
public class FClipboardUtil extends FContext
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
        final ClipboardManager manager = (ClipboardManager) get().getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 获得剪贴板内容
     *
     * @return
     */
    public static CharSequence getText()
    {
        final ClipboardManager manager = (ClipboardManager) get().getSystemService(Context.CLIPBOARD_SERVICE);
        if (!manager.hasPrimaryClip())
            return null;

        final ClipData clipData = manager.getPrimaryClip();
        if (clipData == null)
            return null;

        final int count = clipData.getItemCount();
        if (count <= 0)
            return null;

        final ClipData.Item item = clipData.getItemAt(0);
        if (item == null)
            return null;

        return item.getText();
    }
}
