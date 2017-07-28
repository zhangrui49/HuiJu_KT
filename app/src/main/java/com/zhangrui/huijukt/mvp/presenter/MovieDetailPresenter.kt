package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.tt.lvruheng.eyepetizer.network.RetrofitClient
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.bean.douban.MovieDetail
import com.zhangrui.huijukt.mvp.contract.MovieDetailContract
import com.zhangrui.huijukt.mvp.contract.MovieTabContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MovieDetailPresenter(context: Context, view: MovieDetailContract.View) : MovieDetailContract.Presenter(context, view) {

    override fun requestData(id: String) {
        mView?.showLoading()
        addSubscription(RetrofitClient.getDoubanInstance(mContext!!).create(Api.DoubanApi::class.java)!!.getMovieDetail(id), object : ApiCallBack<MovieDetail>() {

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