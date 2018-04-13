package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.bean.meipai.Video
import com.zhangrui.huijukt.mvp.contract.MeipaiTabContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack
import com.zhangrui.huijukt.net.RetrofitClient

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MeipaiTabPresenter(view: MeipaiTabContract.View) : MeipaiTabContract.Presenter(view) {

    override fun requestData( map: HashMap<String, Any>) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getMeipaiClient().create(Api.MeipaiApi::class.java)!!.getVideos(map), object : ApiCallBack<List<Video>>() {

            override fun onSuccess(data: List<Video>) {
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