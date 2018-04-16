package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.bean.douban.MovieDetail

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class CastContract {
    interface View : BaseView {
        fun showLoading()
        fun showError(msg: String = "请求出错")
        fun showComplete()
        fun showData(data: Casts)
    }

    abstract class Presenter(view: View) : BasePresenter<View>(view) {
        abstract fun requestData(id: String?)
    }
}