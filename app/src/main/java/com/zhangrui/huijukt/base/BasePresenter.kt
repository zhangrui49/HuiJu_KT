package com.zhangrui.huijukt.base

import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription


/**
 *
 * Created by zhangrui on 2017/7/13.
 */
abstract class BasePresenter<V : BaseView> {
    private var mCompositeSubscription: CompositeSubscription? = null

    fun detachView() {
        onUnsubscribe()
    }

    private fun onUnsubscribe() {
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