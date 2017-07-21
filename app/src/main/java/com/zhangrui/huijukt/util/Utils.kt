package com.zhangrui.huijukt.util

import android.app.ActivityManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Environment

/**
 * DESC:
 * Created by zhangrui on 2016/11/2.
 */

object Utils {

    /**
     * 获取版本name

     * @return 当前应用的版本号
     */
    fun getVersion(context: Context): String {
        try {
            val manager = context.packageManager
            val info = manager.getPackageInfo(context.packageName, 0)
            val version = info.versionName
            return version
        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }

    }

    /**
     * 软件版本code

     * @param context 上下文
     * *
     * @return 当前版本Code
     */
    fun getVerCode(context: Context): Int {
        var verCode = -1
        try {
            val packageName = context.packageName
            verCode = context.packageManager.getPackageInfo(packageName, 0).versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return verCode
    }

    /**
     * 网络状态
     * @param context
     * *
     * @return
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context != null) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info != null) {
                return info.isAvailable
            }
        }
        return false
    }

    /**
     * 是否有SDCard

     * @return 是否有SDCard
     */
    fun hasSDCard(): Boolean {

        val status = Environment.getExternalStorageState()
        return status == Environment.MEDIA_MOUNTED
    }

    /**
     * 获取应用运行的最大内存

     * @return 最大内存
     */
    val maxMemory: Long
        get() = Runtime.getRuntime().maxMemory() / 1024

    /**
     * 获取设备的可用内存大小

     * @param context 应用上下文对象context
     * *
     * @return 当前内存大小
     */
    fun getDeviceUsableMemory(context: Context): Int {
        val am = context
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val mi = ActivityManager.MemoryInfo()
        am.getMemoryInfo(mi)
        // 返回当前系统的可用内存
        return (mi.availMem / (1024 * 1024)).toInt()
    }

}
