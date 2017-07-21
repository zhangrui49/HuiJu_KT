package com.zhangrui.huijukt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by zhangrui on 2017/7/20.
 */
class GankPagerAdapter(fragmentManager: FragmentManager, list: ArrayList<Fragment>, title: Array<String>) : FragmentPagerAdapter(fragmentManager) {
    private var mFragments: ArrayList<Fragment>? = null
    private var mTitles: Array<String>? = null

    init {
        this.mFragments = list;
        this.mTitles = title;
    }

    override fun getCount(): Int {
        return mFragments!!?.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitles!![position]
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!!.get(position)
    }

}