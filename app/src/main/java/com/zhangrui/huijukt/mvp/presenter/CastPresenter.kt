package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.mvp.contract.CastContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class CastPresenter(context: Context, view: CastContract.View) : CastContract.Presenter(context, view) {

    override fun requestData(id: String?) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getDoubanInstance(mContext!!).create(Api.DoubanApi::class.java)!!.getCastDetail(id), object : ApiCallBack<Casts>() {

            override fun onSuccess(data: Casts) {
                mView?.showData(data)
            }

            override fun onFailure(msg: String) {
                mView?.showError(msg)
            }

            override fun onFinish() {
                mView?.showComplete()
            }
        })
    }

}