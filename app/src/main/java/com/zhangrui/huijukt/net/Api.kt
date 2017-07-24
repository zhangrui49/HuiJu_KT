package com.zhangrui.huijukt.net

import retrofit2.http.GET
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.bean.GankDay
import com.zhangrui.huijukt.bean.GankDayData
import retrofit2.http.Path
import rx.Observable


/**
 * Created by zhangrui on 2017/7/13.
 */


class Api {
    interface GankApi {

        companion object {
            const val GANK_BASE_URL = "http://gank.io/api/";
        }

        @GET("data/{type}/{pageSize}/{page}")
        fun getGankData(@Path("type") type: String, @Path("pageSize") pageSize: Int=10, @Path("page") page: Int): Observable<Gank>

        @GET("day/{date}")
        fun getGankDayData(@Path("date") date: String): Observable<GankDay>
    }

}