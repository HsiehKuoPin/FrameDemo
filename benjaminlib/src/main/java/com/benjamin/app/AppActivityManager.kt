package com.benjamin.app

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Activity栈任务管理器
 */
class AppActivityManager private constructor() {

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return try {
            activityStack!!.lastElement()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    /**
     * 获取当前Activity的前一个Activity
     */
    fun preActivity(): Activity? {
        val index = activityStack!!.size - 2
        return if (index < 0) null else activityStack!![index]
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        if (activityStack!!.contains(activity)) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    /**
     * 移除指定的Activity
     */
    fun removeActivity(activity: Activity) {
        if (activityStack!!.contains(activity)) {
            activityStack!!.remove(activity)
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<out Activity>) {
        activityStack!!.filter { it.javaClass == cls }.forEach { finishActivity(it) }

    }

    /**
     * 结束所有Activity
     *
     */
    fun finishAllActivity() {
        finishAll(null)
    }

    /**
     * 结束所有Activity，除了指定的一个Activity
     *
     */
    fun finishAllExceptActivity(exceptClass: Class<out Activity>) {
        finishAll(exceptClass)
    }

    fun finishAll(exceptClass: Class<out Activity>?) {
        activityStack!!.filter { it.javaClass != exceptClass }.forEach { it.finish() }
        activityStack!!.clear()
    }

    /**
     * 返回到指定的activity
     *
     */
    fun returnToActivity(cls: Class<out Activity>) {
        while (activityStack!!.size != 0)
            if (activityStack!!.peek().javaClass == cls) {
                break
            } else {
                finishActivity(activityStack!!.peek())
            }
    }


    /**
     * 退出应用程序
     */
    fun appExit(context: Context) {
        //先清除Activity
        finishAllActivity()
        val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //再killProcesses
        activityMgr.restartPackage(context.packageName)
        System.exit(0)
    }

    companion object {
        private var activityStack: Stack<Activity>? = null
        private var instance: AppActivityManager? = null

        /**
         * 单一实例
         */
        val appManager: AppActivityManager
            get() {
                if (instance == null) {
                    instance = AppActivityManager()
                    activityStack = Stack()
                }
                return instance as AppActivityManager
            }
    }
}