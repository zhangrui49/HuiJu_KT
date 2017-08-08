package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.bean.meipai.Video
import com.zhangrui.huijukt.mvp.contract.MeipaiTabContract
import com.zhangrui.huijukt.mvp.contract.MovieTabContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MeipaiTabPresenter(context: Context, view: MeipaiTabContract.View) : MeipaiTabContract.Presenter(context, view) {

    override fun requestData( map: HashMap<String, Any>) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getMeipaiClient(mContext!!).create(Api.MeipaiApi::class.java)!!.getVideos(map), object : ApiCallBack<List<Video>>() {

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