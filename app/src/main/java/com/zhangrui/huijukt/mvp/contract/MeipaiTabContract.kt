package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.bean.meipai.Video

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MeipaiTabContract {
    interface View : BaseView {
        fun showLoading()
        fun showError(msg: String = "请求出错")
        fun showComplete()
        fun showData(data: List<Video>)
    }

    abstract class Presenter(context: Context, view: View) : BasePresenter<View>(context, view) {
        abstract fun requestData(map: HashMap<String, Any>)
    }
}