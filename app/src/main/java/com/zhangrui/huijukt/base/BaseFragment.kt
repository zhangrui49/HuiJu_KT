package com.zhangrui.huijukt.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangrui.huijukt.R

/**
 *
 * Created by zhangrui on 2017/7/20.
 */
abstract class BaseFragment<T : BasePresenter<out BaseView>> : Fragment() {
    var mPresenter: T? = null
    var mRootView: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater?.inflate(generateLayoutId(), container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter = generatePresenter()
        initView()
    }

    abstract fun initView()

    abstract fun generateLayoutId(): Int

    abstract fun generatePresenter(): T

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
    }
}