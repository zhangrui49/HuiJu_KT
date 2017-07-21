package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import android.util.Log
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.mvp.contract.GankTabContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 * Created by zhangrui on 2017/7/20.
 */
class GankTabPresenter(context: Context,view: GankTabContract.View) :GankTabContract.Presenter(context,view) {
    init {
        mContext = context
        mView = view;
    }

    override fun requestData(path: String, pageSize: Int, page: Int) {
        mView?.showLoading();
        addSubscription(RetrofitClient.getInstance(mContext!!, Api.GankApi.GANK_BASE_URL).create(Api.GankApi::class.java)!!.getGankData(path, pageSize, page), object : ApiCallBack<Gank>() {

            override fun onSuccess(data: Gank) {
                mView?.showData(data)
            }

            override fun onFailure(msg: String) {
                mView?.showError(msg);
            }

            override fun onFinish() {
                mView?.showComplete()
            }
        })

    }

}