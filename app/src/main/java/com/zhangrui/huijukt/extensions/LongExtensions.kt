package com.zhangrui.huijukt.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by zhangrui on 2017/7/11.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}