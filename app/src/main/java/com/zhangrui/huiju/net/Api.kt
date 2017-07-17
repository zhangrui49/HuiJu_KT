package com.zhangrui.huiju.net

import retrofit2.http.GET
import com.zhangrui.huiju.bean.Gank
import com.zhangrui.huiju.bean.GankDayData
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

        @GET("day/{year}/{month}/{day}")
        fun getGankDayData(@Path("year") year: Int, @Path("month") month: Int,
                           @Path("day") day: Int): Observable<GankDayData>
    }

}