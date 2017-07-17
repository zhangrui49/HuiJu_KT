package com.zhangrui.huiju.mvp.presenter

import android.content.Context
import android.util.Log
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huiju.base.BaseModel
import com.zhangrui.huiju.base.BasePresenter
import com.zhangrui.huiju.base.BaseView
import com.zhangrui.huiju.bean.Gank
import com.zhangrui.huiju.mvp.contract.GankContract
import com.zhangrui.huiju.mvp.model.GankModel
import com.zhangrui.huiju.net.Api
import com.zhangrui.huiju.net.ApiCallBack
import rx.Subscriber

/**
 *
 * Created by zhangrui on 2017/7/13.
 */
class GankPresenter(context: Context, view: GankContract.View) : GankContract.Presenter(context, view) {

    init {
        mContext = context
        mView = view;
    }

    override fun requestData(path: String, pageSize: Int, page: Int) {
        mView?.showLoading();
        Log.e("http",page.toString());
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