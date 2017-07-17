package com.zhangrui.huiju.net

import rx.Subscriber



/**
 * Created by zhangrui on 2017/7/14.
 */
abstract class ApiCallBack<T> : Subscriber<T>() {

    abstract fun onSuccess(data: T)

    abstract fun onFinish()

    abstract fun onFailure(msg: String="请求出错")

    override fun onError(e: Throwable) {

        onFailure(e.message?:"请求出错")
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onCompleted() {
        onFinish()
    }
}
