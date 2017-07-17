package com.zhangrui.huiju.activity

import android.Manifest
import android.os.Build
import android.os.Handler
import android.support.v7.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.footer.BallPulseView
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout
import com.lcodecore.tkrefreshlayout.header.bezierlayout.WaveView
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.tbruyelle.rxpermissions.RxPermissions
import com.zhangrui.huiju.R
import com.zhangrui.huiju.adapter.GankAdapter
import com.zhangrui.huiju.base.BaseActivity
import com.zhangrui.huiju.bean.Gank
import com.zhangrui.huiju.bean.GankData
import com.zhangrui.huiju.extensions.*
import com.zhangrui.huiju.mvp.contract.GankContract
import com.zhangrui.huiju.mvp.presenter.GankPresenter
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.common_data_layout.*
import org.jetbrains.anko.ctx


/**
 * Created by zhangrui on 2017/7/14.
 */
class GankActivity : BaseActivity<GankPresenter>(), GankContract.View {
    var page = 1
    val PAGE_SIZE = 10
    var gankAdapter: GankAdapter? = null;
    var list: ArrayList<GankData>? = null
    override fun showLoading() {
        // showProgress()
    }

    override fun showError(msg: String) {
        toast(msg)
    }

    override fun showComplete() {
        dismissProgress()
        tkRefreshLayout.finishRefreshing()
        tkRefreshLayout.finishLoadmore()
    }

    override fun showData(data: Gank) {
        //     tkRefreshLayout.finishRefreshing()
        // tkRefreshLayout.finishLoadmore()
        data.results?.let { list!!.addAll(it) };
        gankAdapter?.notifyDataSetChanged()
    }

    override fun generateLayoutId(): Int {
        return R.layout.common_data_layout
    }

    override fun generatePresenter(): GankPresenter {
        return GankPresenter(this, this);
    }

    override fun initView() {
        list = ArrayList<GankData>()
        gankAdapter = GankAdapter(this, R.layout.item_gank_img, list!!);
        val rxPermissions = RxPermissions(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val subscribe = rxPermissions
                    .request(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe({ granted ->
                        if (granted) {

                        } else {
                            warn("请授予网络权限!")
                        }
                    })
        }
        recyclerview.adapter = gankAdapter;
        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        tkRefreshLayout.setHeaderView(ProgressLayout(this))
        tkRefreshLayout.setBottomHeight(180f.dip2px(this))
        tkRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                page = 1;
                list?.clear()
                //     Handler().postDelayed(Runnable { refreshLayout?.finishRefreshing() }, 2000)
                getGankData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                page++;
                //  Handler().postDelayed(Runnable { refreshLayout?.finishLoadmore() }, 2000)
                getGankData()
            }
        })
        tkRefreshLayout.startRefresh()
    }

    fun getGankData() {
        mPresenter?.requestData("福利", PAGE_SIZE, page)
    }
}