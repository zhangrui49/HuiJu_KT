package com.zhangrui.huiju.base

import android.support.annotation.UiThread

/**
 * Created by zhangrui on 2017/7/13.
 */
@UiThread
interface BaseView {
    fun showLoading()
    fun showError(msg:String="请求出错")
    fun showComplete()
}