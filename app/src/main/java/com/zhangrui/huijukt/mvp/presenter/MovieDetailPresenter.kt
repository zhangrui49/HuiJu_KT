package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhangrui.huijukt.mvp.contract.MovieDetailContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack
import com.zhangrui.huijukt.net.RetrofitClient

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieDetailPresenter (view: MovieDetailContract.View): MovieDetailContract.Presenter(view) {

    override fun requestData(id: String) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getDoubanClient().create(Api.DoubanApi::class.java)!!.getMovieDetail(id), object : ApiCallBack<MovieDetail>() {

            override fun onSuccess(data: MovieDetail) {
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