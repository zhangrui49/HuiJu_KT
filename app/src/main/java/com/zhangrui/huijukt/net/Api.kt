package com.zhangrui.huijukt.net

import retrofit2.http.GET
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.bean.GankDay
import com.zhangrui.huijukt.bean.douban.Avatars
import com.zhangrui.huijukt.bean.douban.Casts
import com.zhangrui.huijukt.bean.douban.Movie
import retrofit2.http.Path
import rx.Observable
import retrofit2.http.QueryMap
import com.zhangrui.huijukt.bean.douban.MovieDetail


/**
 * Created by zhangrui on 2017/7/13.
 */


class Api {
    interface GankApi {

        companion object {
            const val GANK_BASE_URL = "http://gank.io/api/"
        }

        @GET("data/{type}/{pageSize}/{page}")
        fun getGankData(@Path("type") type: String, @Path("pageSize") pageSize: Int = 10, @Path("page") page: Int): Observable<Gank>

        @GET("day/{date}")
        fun getGankDayData(@Path("date") date: String): Observable<GankDay>
    }

    interface DoubanApi {

        companion object {
            val DOUBAN_BASE_URL = "https://api.douban.com/v2/movie/"
        }

        @GET("{type}")
        fun getMovies(@Path("type") type: String, @QueryMap map: HashMap<String, Any>): Observable<Movie>

        @GET("subject/{id}")
        fun getMovieDetail(@Path("id") id: String): Observable<MovieDetail>

        @GET("celebrity/{id}")
        fun getCastDetail(@Path("id") id: String?): Observable<Casts>
    }

}