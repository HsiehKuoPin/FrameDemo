package com.benjamin.utils.eighteen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

public class UtilsInitializer {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private UtilsInitializer() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            UtilsInitializer.context = context;
            Log.w("UtilsInitializer","UtilsInitializer init may be memory leak");
        } else {
            UtilsInitializer.context = applicationContext;
        }
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}