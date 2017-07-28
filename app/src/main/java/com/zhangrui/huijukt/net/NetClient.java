package com.zhangrui.huijukt.net;


import com.zhangrui.huijukt.App;
import com.zhangrui.huijukt.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DESC:
 * Created by zhangrui on 2016/11/3.
 */
public class NetClient {

    private volatile static NetClient mInstance = new NetClient();

    private static Retrofit sAiPaiRetrofit;
    private static Retrofit sGankRetrofit;
    private static Retrofit sZhihuRetrofit;
    private static Retrofit sDoubanRetrofit;
    private OkHttpClient sOkHttpClient;

    public static NetClient getInstance() {
        if (mInstance == null) {
            synchronized (NetClient.class) {
                if (mInstance == null) {
                    mInstance = new NetClient();
                }
            }
        }
        return mInstance;
    }

    private NetClient() {
    }

    /**
     * 美拍
     */
    public Retrofit getVideoRetrofit() {

        if (sAiPaiRetrofit == null) {
            sAiPaiRetrofit = new Retrofit.Builder()
                  //  .baseUrl(Api.AiPaiApi.AIPAI_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sAiPaiRetrofit;

    }

    /**
     * Gank
     */
    public Retrofit getGankRetrofit() {

        if (sGankRetrofit == null) {
            sGankRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.GankApi.GANK_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sGankRetrofit;
    }

    /**
     * Zhihu
     */
    public Retrofit getZhihuRetrofit() {

        if (sZhihuRetrofit == null) {
            sZhihuRetrofit = new Retrofit.Builder()
                 //   .baseUrl(Api.ZhihuApi.ZHIHU_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sZhihuRetrofit;
    }

    /**
     * 豆瓣
     */
    public Retrofit getDoubanRetrofit() {

        if (sDoubanRetrofit == null) {
            sDoubanRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.DoubanApi.Companion.getDOUBAN_BASE_URL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return sDoubanRetrofit;
    }


    /**
     * 初始化okhttp
     */
    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            //cache url
            File httpCacheDirectory = new File(App.instance.getExternalCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            builder.cache(cache);
            builder.connectTimeout(15, TimeUnit.SECONDS);
            sOkHttpClient = builder.build();
        }
        return sOkHttpClient;
    }


}
