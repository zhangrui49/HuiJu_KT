package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView

/**
 * Created by zhangrui on 2017/7/13.
 */
class MeipaiContract {
    interface View : BaseView

    abstract class Presenter(view: View) : BasePresenter<View>(view)

}