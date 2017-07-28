package com.zhangrui.huijukt.base

/**
 *
 * Created by zhangrui on 2017/7/28.
 */
abstract class LazyFragment : BaseFragment<BasePresenter<out BaseView>>() {
    var isVisibleToUser = false
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
    }

    abstract fun refresh()
}