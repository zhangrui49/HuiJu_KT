package com.zhangrui.huiju.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by zhangrui on 2017/6/7.
 */
abstract class BaseActivity<T> : AppCompatActivity() {
    var mPresenter: T? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(generateLayoutId())
        mPresenter = generatePresenter()
        initView()
    }

    abstract fun initView();

    abstract fun generateLayoutId(): Int;

    abstract fun generatePresenter(): T;
}