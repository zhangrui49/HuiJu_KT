package com.zhangrui.huijukt.mvp.presenter

import android.content.Context
import com.zhangrui.huijukt.bean.douban.Movie
import com.zhangrui.huijukt.mvp.contract.MovieContract
import com.zhangrui.huijukt.net.Api
import com.zhangrui.huijukt.net.ApiCallBack

/**
 *
 * Created by zhangrui on 2017/7/27.
 */
class MoviePresenter(view: MovieContract.View) : MovieContract.Presenter(view)