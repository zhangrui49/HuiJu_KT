package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.gank.GankDay
import com.zhangrui.huijukt.mvp.contract.GankDayContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 *
 * Created by zhangrui on 2017/7/21.
 */
class GankDayPresenter(context: Context, view: GankDayContract.View) : GankDayContract.Presenter(context, view) {

    init {
        mContext = context
        mView = view;
    }

    override fun requestGankDayData(date: String) {
        mView?.showLoading();
        addSubscription(RetrofitClient.getGankClient(mContext!!).create(Api.GankApi::class.java)!!.getGankDayData(date), object : ApiCallBack<GankDay>() {

            override fun onSuccess(data: GankDay) {
                if (data.isError || data.category.size < 1) {
                    mView?.showEmpty()
                } else {
                    mView?.showData(data)
                }
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