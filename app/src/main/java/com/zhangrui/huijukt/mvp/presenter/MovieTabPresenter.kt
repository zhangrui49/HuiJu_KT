package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.mvp.contract.MovieTabContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack
import com.zhangrui.huijukt.net.RetrofitClient

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieTabPresenter(view: MovieTabContract.View) : MovieTabContract.Presenter(view) {

    override fun requestData(type: String, map: HashMap<String, Any>) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getDoubanClient().create(Api.DoubanApi::class.java)!!.getMovies(type, map), object : ApiCallBack<Movie>() {

            override fun onSuccess(data: Movie) {
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