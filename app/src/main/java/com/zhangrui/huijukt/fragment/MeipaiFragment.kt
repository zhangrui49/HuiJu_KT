package com.zhangrui.huijukt.fragment

import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.mvp.contract.MeipaiContract
import com.zhangrui.huijukt.mvp.presenter.MeipaiPresenter
import org.jetbrains.anko.support.v4.ctx
import com.zhangrui.huijukt.adapter.FragmentAdapter
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_video.*


/**
 *
 * Created by zhangrui on 2017/7/20.
 */
class MeipaiFragment : BaseFragment<MeipaiPresenter>(), MeipaiContract.View {
    private val TITLES = arrayOf("热门", "搞笑", "明星名人", "男神", "女神", "音乐", "舞蹈", "美食", "美妆时尚", "宝宝", "萌宠乐园")
    private val IDS = arrayOf("1", "13", "16", "31", "19", "62", "63", "59", "27", "18", "6")
    override fun initView() {

        val mFragments = ArrayList<Fragment>()
        for (i in 0..TITLES.size - 1) {
            val videoListFragment = MeipaiTabFragment()
            val bundle = Bundle()
            bundle.putString("id", IDS[i])
            bundle.putString("title", TITLES[i])
            videoListFragment.arguments = bundle
            mFragments.add(videoListFragment)
        }
        val adapter = FragmentAdapter(childFragmentManager, mFragments, TITLES)
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 1
        tab.setupWithViewPager(view_pager)
    }

    override fun generateLayoutId(): Int {
        return R.layout.fragment_video
    }

    override fun generatePresenter(): MeipaiPresenter {
        return MeipaiPresenter(this)
    }
}