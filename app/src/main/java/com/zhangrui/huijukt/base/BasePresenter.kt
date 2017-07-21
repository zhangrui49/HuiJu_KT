package com.zhangrui.huijukt.base

import android.content.Context
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription


/**
 *
 * Created by zhangrui on 2017/7/13.
 */
abstract class BasePresenter<V>(context: Context,view:V) {
    var mView:V? = null;
    var mContext: Context? = null
    var mCompositeSubscription: CompositeSubscription? = null


    fun detachView() {
        this.mView = null
        onUnsubscribe()
    }

    fun onUnsubscribe() {
        if (mCompositeSubscription != null && (mCompositeSubscription as CompositeSubscription).hasSubscriptions()) {
            (mCompositeSubscription as CompositeSubscription).unsubscribe()
        }
    }


    fun <T> addSubscription(observable: Observable<T>, subscriber: Subscriber<T>) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeSubscription()
        }
        mCompositeSubscription?.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber))
    }
}