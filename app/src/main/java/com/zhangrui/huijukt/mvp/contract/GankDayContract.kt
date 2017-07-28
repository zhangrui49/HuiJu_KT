package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.GankDay

/**
 * Created by zhangrui on 2017/7/21.
 */
class GankDayContract {

    interface View : BaseView {
        fun showLoading()
        fun showError(msg: String = "请求出错")
        fun showComplete()
        fun showData(data: GankDay)
        fun showEmpty()
    }

    abstract class Presenter(context: Context, view: View) : BasePresenter<View>(context, view) {
        abstract fun requestGankDayData(
                date: String)
    }
}