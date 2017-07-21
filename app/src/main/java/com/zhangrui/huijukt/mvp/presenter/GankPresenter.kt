package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import android.util.Log
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.mvp.contract.GankContract
import com.zhangrui.huijukt.mvp.contract.WelfareContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

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