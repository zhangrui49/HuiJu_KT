package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView

/**
 * Created by zhangrui on 2017/7/20.
 */
class HomeContract {

    interface View : BaseView {

    }

    abstract class Presenter(context: Context, view: HomeContract.View) : BasePresenter<View>(context,view){


    }
}