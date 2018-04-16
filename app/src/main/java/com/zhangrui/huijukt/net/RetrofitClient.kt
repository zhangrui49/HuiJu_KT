package com.zhangrui.huijukt.net

import android.util.Log
import com.zhangrui.huijukt.App
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 */
class RetrofitClient private constructor(baseUrl: String) {
    var httpCacheDirectory: File? = null
    var cache: Cache? = null
    var okHttpClient: OkHttpClient? = null
    var retrofit: Retrofit? = null
    val DEFAULT_TIMEOUT: Long = 15
    val url = baseUrl

    init {
        //缓存地址
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(App.instance.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e("OKHttp", "Could not create http cache", e)
        }
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                // .addInterceptor(CacheInterceptor(context))
                .addNetworkInterceptor(CacheInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build()

    }

    companion object {


        var gankInstance: RetrofitClient? = null

        var doubanInstance: RetrofitClient? = null

        var meipaiInstance: RetrofitClient? = null



        fun getGankClient(): RetrofitClient {
            if (gankInstance == null) {
                synchronized(RetrofitClient::class) {
                    if (gankInstance == null) {
                        gankInstance = RetrofitClient(Api.GankApi.GANK_BASE_URL)
                    }
                }
            }
            return gankInstance!!
        }

        fun getMeipaiClient(): RetrofitClient {
            if (meipaiInstance == null) {
                synchronized(RetrofitClient::class) {
                    if (meipaiInstance == null) {
                        meipaiInstance = RetrofitClient(Api.MeipaiApi.MEIPAI_BASE_URL)
                    }
                }
            }
            return meipaiInstance!!
        }

        fun getDoubanClient(): RetrofitClient{
            if (doubanInstance == null) {
                synchronized(RetrofitClient::class) {
                    if (doubanInstance == null) {
                        doubanInstance = RetrofitClient(Api.DoubanApi.DOUBAN_BASE_URL)
                    }
                }
            }
            return doubanInstance!!
        }
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }


}