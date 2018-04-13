package com.zhangrui.huijukt.mvp.contract

import android.content.Context
import com.zhangrui.huijukt.base.BaseModel
import com.zhangrui.huijukt.base.BasePresenter
import com.zhangrui.huijukt.base.BaseView

/**
 * Created by zhangrui on 2017/7/13.
 */
class GankContract {
    interface View : BaseView

    abstract class Presenter( view: GankContract.View) : BasePresenter<View>( view)

    interface Model : BaseModel
}