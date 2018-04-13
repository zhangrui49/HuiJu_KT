package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.mvp.contract.CastContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack
import com.zhangrui.huijukt.net.RetrofitClient

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class CastPresenter( view: CastContract.View) : CastContract.Presenter(view) {

    override fun requestData(id: String?) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getDoubanClient().create(Api.DoubanApi::class.java)!!.getCastDetail(id), object : ApiCallBack<Casts>() {

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