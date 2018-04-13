package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import android.util.Log
import com.zhangrui.huijukt.bean.gank.Gank
import com.zhangrui.huijukt.mvp.contract.WelfareContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack
import com.zhangrui.huijukt.net.RetrofitClient

/**
 *
 * Created by zhangrui on 2017/7/13.
 */
class WelfarePresenter(view: WelfareContract.View) : WelfareContract.Presenter(view) {

    init {
        mView = view
    }

    override fun requestData(path: String, pageSize: Int, page: Int) {
        mView?.showLoading();
        Log.e("http",page.toString());
        addSubscription(RetrofitClient.getGankClient().create(Api.GankApi::class.java)!!.getGankData(path, pageSize, page), object : ApiCallBack<Gank>() {

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