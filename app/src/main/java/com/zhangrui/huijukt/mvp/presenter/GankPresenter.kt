package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.mvp.contract.GankContract

/**
 *
 * Created by zhangrui on 2017/7/13.
 */
class GankPresenter(context: Context, view: GankContract.View) : GankContract.Presenter(context, view) {

    init {
        mContext = context
        mView = view;
    }


}