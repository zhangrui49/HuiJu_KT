package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BaseModel
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.gank.Gank

/**
 * Created by zhangrui on 2017/7/13.
 */
class WelfareContract {
    interface View : BaseView {
        fun showLoading()
        fun showError(msg: String = "请求出错")
        fun showComplete()
        fun showData(data: Gank)
    }

    abstract class Presenter(view: View) : BasePresenter<View>(view) {

        abstract fun requestData(path: String, pageSize: Int, page: Int)
    }

    interface Model : BaseModel
}