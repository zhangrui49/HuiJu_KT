package com.zhangrui.huijukt.activity

import android.Manifest
import android.content.Context
import android.os.Build
import android.support.v7.widget.StaggeredGridLayoutManager
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.tbruyelle.rxpermissions.RxPermissions
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.WelfareAdapter
import com.zhangrui.huijukt.base.BaseActivity
import com.zhangrui.huijukt.bean.Gank
import com.zhangrui.huijukt.bean.GankData
import com.zhangrui.huijukt.extensions.*
import com.zhangrui.huijukt.mvp.contract.WelfareContract
import kotlinx.android.synthetic.main.common_data_layout.*
import org.jetbrains.anko.ctx
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.zhangrui.huijukt.mvp.presenter.WelfarePresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter


/**
 * Created by zhangrui on 2017/7/14.
 */
class WelfareActivity : BaseActivity<WelfarePresenter>(), WelfareContract.View {
    var page = 1
    val PAGE_SIZE = 10
    var welfareAdapter: WelfareAdapter? = null;
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
        welfareAdapter?.notifyDataSetChanged()
    }

    override fun generateLayoutId(): Int {
        return R.layout.common_data_layout
    }

    override fun generatePresenter(): WelfarePresenter {
        return WelfarePresenter(this, this);
    }

    override fun initView() {
        list = ArrayList<GankData>()
        welfareAdapter = WelfareAdapter(this, R.layout.item_gank_img, list!!);
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
        recyclerview.adapter = welfareAdapter;
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
        welfareAdapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                startPhotoActivity(ctx, (view as RelativeLayout).getChildAt(0) as ImageView,list!!.get(position).url!!)
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    fun getGankData() {
        mPresenter?.requestData("福利", PAGE_SIZE, page)
    }

    fun startPhotoActivity(context: Context, imageView: ImageView,url:String) {
        val intent = Intent(context, ImageActivity::class.java)
        val location = IntArray(2)

        imageView.getLocationOnScreen(location)
        intent.putExtra("left", location[0])
        intent.putExtra("top", location[1])
        intent.putExtra("height", imageView.getHeight())
        intent.putExtra("width", imageView.getWidth())
        intent.putExtra("url",url)

        context.startActivity(intent)
        overridePendingTransition(0, 0)
    }
}