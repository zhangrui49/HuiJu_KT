package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BaseModel
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView
import com.zhangrui.huijukt.bean.Gank

/**
 * Created by zhangrui on 2017/7/13.
 */
class GankContract {
    interface View : BaseView {

    }

    abstract class Presenter(context: Context, view: GankContract.View) : BasePresenter<View>(context, view) {

    }

    interface Model : BaseModel {

    }
}