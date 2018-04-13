package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieContract {
    interface View : BaseView

    abstract class Presenter(view: MovieContract.View) : BasePresenter<View>(view)
}