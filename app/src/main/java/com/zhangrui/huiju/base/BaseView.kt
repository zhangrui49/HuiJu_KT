package com.zhangrui.huiju.base

/**
 * Created by zhangrui on 2017/7/13.
 */
interface BaseView {
    fun showLoading()
    fun showError(msg:String="请求出错")
    fun showComplete()
}