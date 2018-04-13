package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.mvp.contract.GankContract

/**
 *
 * Created by zhangrui on 2017/7/13.
 */
class GankPresenter( view: GankContract.View) : GankContract.Presenter( view) {

    init {
        mView = view;
    }


}