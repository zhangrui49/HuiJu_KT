package com.zhangrui.huiju.extensions

import android.content.Context

/**
 * Created by zhangrui on 2017/7/17.
 */
fun Float.px2dip(context: Context): Float {
    val scale: Float = context.resources.displayMetrics.density
    return this * scale + 0.5f
}

fun Float.dip2px(context: Context): Float {
    val scale: Float = context.resources.displayMetrics.density
    return this / scale + 0.5f
}