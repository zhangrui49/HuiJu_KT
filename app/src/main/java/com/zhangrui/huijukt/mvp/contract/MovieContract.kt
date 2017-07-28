package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.bean.douban.Movie

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieContract {
    interface View : BaseView

    abstract class Presenter(context: Context, view: MovieContract.View) : BasePresenter<View>(context, view)
}