package com.benjamin.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * Desc: 复制黏贴
 * Date: 2015/10/21.
 */
public class ClipUtil {

    /**
     * 复制
     *
     * @param context
     * @param str
     */
    public static void copy(Context context, String str) {
        copy(context, str, false);
    }

    /**
     * 复制
     *
     * @param context
     * @param str
     */
    public static void copy(Context context, String str, boolean showToast) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(str); // 复制
        if (showToast) Toast.makeText(context, "文本已复制", Toast.LENGTH_SHORT).show();
    }

    /**
     * 复制
     *
     * @param context
     * @param str
     */
    public static void copy(Context context, String str, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(str); // 复制
        if (text != null) Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 黏贴
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return clip.getText().toString(); //黏贴
    }
}
