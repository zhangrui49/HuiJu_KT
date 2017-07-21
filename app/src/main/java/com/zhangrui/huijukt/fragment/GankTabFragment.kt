package com.zhangrui.huijukt.fragment

import android.Manifest
import android.os.Build
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.tbruyelle.rxpermissions.RxPermissions
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.GankTabAdapter
import com.zhangrui.huijukt.adapter.WelfareAdapter
import com.zhangrui.huijukt.base.BaseFragment
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.bean.GankData
import com.zhangrui.huijukt.extensions.dip2px
import com.zhangrui.huijukt.extensions.warn
import com.zhangrui.huijukt.mvp.contract.GankTabContract
import com.zhangrui.huijukt.mvp.presenter.GankTabPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.common_data_layout.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.support.v4.ctx

/**
 * Created by zhangrui on 2017/7/20.
 */
class GankTabFragment: BaseFragment<GankTabPresenter>(),GankTabContract.View {
    var page = 1
    val PAGE_SIZE = 10
    var gankTabAdapter: GankTabAdapter? = null;
    var list: ArrayList<GankData>? = null

    override fun showLoading() {
    }

    override fun showError(msg: String) {
    }

    override fun showComplete() {
        tkRefreshLayout.finishRefreshing()
        tkRefreshLayout.finishLoadmore()
    }

    override fun showData(data: Gank) {
        data.results?.let { list!!.addAll(it) };
        gankTabAdapter?.notifyDataSetChanged()
    }

    override fun initView() {
        list = ArrayList<GankData>()
        gankTabAdapter = GankTabAdapter(ctx, R.layout.item_gank_data, list!!);
        val rxPermissions = RxPermissions(activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val subscribe = rxPermissions
                    .request(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe({ granted ->
                        if (granted) {

                        } else {
                            activity.warn("请授予网络权限!")
                        }
                    })
        }
        recyclerview.adapter = gankTabAdapter;
        recyclerview.layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL,false)
        tkRefreshLayout.setHeaderView(ProgressLayout(ctx))
        tkRefreshLayout.setBottomHeight(180f.dip2px(ctx))
        tkRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                page = 1;
                list?.clear()
                getGankData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
                page++;

                getGankData()
            }
        })
        tkRefreshLayout.startRefresh()
        gankTabAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {

            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.common_data_layout
    }

    override fun generatePresenter(): GankTabPresenter {
        return GankTabPresenter(ctx,this)
    }


    fun getGankData() {
        var type=arguments!!.getString("type")
        mPresenter?.requestData(type, PAGE_SIZE, page)
    }

}