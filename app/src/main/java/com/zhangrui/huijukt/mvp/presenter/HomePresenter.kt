package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.mvp.contract.HomeContract

/**
 * Created by zhangrui on 2017/7/20.
 */
class HomePresenter(context: Context, view: HomeContract.View) : HomeContract.Presenter(context,view) {

    init {
        mContext = context
        mView = view;
    }
}