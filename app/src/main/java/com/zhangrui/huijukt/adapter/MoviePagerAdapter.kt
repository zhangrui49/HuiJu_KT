package com.zhangrui.huijukt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zhangrui on 2017/7/20.
 */
class MoviePagerAdapter(fragmentManager: FragmentManager, list: ArrayList<Fragment>) : FragmentPagerAdapter(fragmentManager) {
    private var mFragments: ArrayList<Fragment>? = null

    init {
        this.mFragments = list
    }

    override fun getCount(): Int {
        return mFragments!!?.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

}