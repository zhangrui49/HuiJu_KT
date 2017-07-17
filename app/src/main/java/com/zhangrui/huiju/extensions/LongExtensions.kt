package com.zhangrui.huiju.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by zhangrui on 2017/7/11.
 */
fun Long.toDateString(dateFormate: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormate, Locale.getDefault())
    return df.format(this)
}