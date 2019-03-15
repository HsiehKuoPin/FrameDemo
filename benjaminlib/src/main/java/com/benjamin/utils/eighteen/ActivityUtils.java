package com.benjamin.utils.eighteen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/23
 *     desc  : Activity相关工具类
 * </pre>
 */
public class ActivityUtils {

    private static Context context = UtilsInitializer.getContext();

    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断是否存在Activity
     *
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }


    /**
     * 打开Activity
     *
     * @param activityClass class
     */
    public static void launchActivity(Class<? extends Activity> activityClass) {
        launchActivity(context, activityClass);
    }

    /**
     * 打开Activity
     *
     * @param activityClass class
     */
    public static void launchActivity(Context context, Class<? extends Activity> activityClass) {
        launchActivity(context, activityClass, null);
    }


    /**
     * 打开Activity
     *
     * @param activityClass class
     */
    public static void launchActivity(Class<? extends Activity> activityClass, Bundle bundle) {
        launchActivity(context, context.getPackageName(), activityClass.getName(), bundle);
    }

    /**
     * 打开Activity
     *
     * @param activityClass class
     */
    public static void launchActivity(Context context, Class<? extends Activity> activityClass, Bundle bundle) {
        launchActivity(context, context.getPackageName(), activityClass.getName(), bundle);
    }

    /**
     * 打开Activity
     *
     * @param packageName 包名
     * @param className   全类名
     */
    public static void launchActivity(String packageName, String className) {
        launchActivity(context, packageName, className);
    }

    /**
     * 打开Activity
     *
     * @param packageName 包名
     * @param className   全类名
     */
    public static void launchActivity(Context context, String packageName, String className) {
        launchActivity(context, packageName, className, null);
    }

    /**
     * 打开Activity
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void launchActivity(String packageName, String className, Bundle bundle) {
        launchActivity(context, packageName, className, bundle);
    }

    /**
     * 打开Activity
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void launchActivity(Context context, String packageName, String className, Bundle bundle) {
        context.startActivity(IntentUtils.getComponentIntent(packageName, className, bundle));
    }

    /**
     * 获取launcher activity
     *
     * @param packageName 包名
     * @return launcher activity
     */
    public static String getLauncherActivity(String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : infos) {
            if (info.activityInfo.packageName.equals(packageName)) {
                return info.activityInfo.name;
            }
        }
        return "no " + packageName;
    }
}
