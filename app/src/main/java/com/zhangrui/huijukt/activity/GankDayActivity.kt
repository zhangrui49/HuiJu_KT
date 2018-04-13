package com.zhangrui.huijukt.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.borax12.materialdaterangepicker.date.DatePickerDialog
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.adapter.GankDayAdapter
import com.zhangrui.huijukt.base.BaseActivity
import com.zhangrui.huijukt.bean.gank.GankDay
import com.zhangrui.huijukt.extensions.dip2px
import com.zhangrui.huijukt.mvp.contract.GankDayContract
import com.zhangrui.huijukt.mvp.presenter.GankDayPresenter
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter
import kotlinx.android.synthetic.main.activity_gank_day.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GankDayActivity : BaseActivity<GankDayPresenter>(), GankDayContract.View, DatePickerDialog.OnDateSetListener {
    var list: ArrayList<GankDay.Results.Return>? = null
    var adapter: MultiItemTypeAdapter<GankDay.Results.Return>? = null;
    override fun showLoading() {
    }

    override fun showError(msg: String) {

    }

    override fun showComplete() {
        tkRefreshLayout.finishRefreshing()
    }

    override fun showData(data: GankDay) {
        data.generateResults()?.let {
            list?.clear()
            list!!.addAll(it)
        };
        adapter?.notifyDataSetChanged()
    }

    override fun showEmpty() {
        toast("该日暂无数据")
    }

    override fun initView() {
        setSupportActionBar(toolbar)
        toolbar.title = "福利"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener {
            finish()
        }
        list = ArrayList();
        adapter = MultiItemTypeAdapter(this, list)
        adapter?.addItemViewDelegate(GankDayAdapter.ContentItemDelegate())
        adapter?.addItemViewDelegate(GankDayAdapter.TitleItemDelegate())
        adapter?.addItemViewDelegate(GankDayAdapter.ImageItemDelegate())
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        tkRefreshLayout.setHeaderView(ProgressLayout(this))
        tkRefreshLayout.setBottomHeight(180f.dip2px(this))
        tkRefreshLayout.setOnRefreshListener(object : RefreshListenerAdapter() {
            override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                getGankDayData()
            }

            override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {

            }
        })
        tkRefreshLayout.setEnableLoadmore(false)
        tkRefreshLayout.startRefresh()
        adapter?.setOnItemClickListener(object : MultiItemTypeAdapter.OnItemClickListener {

            override fun onItemClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int) {
                val item = list?.get(position)
                if (item?.type.equals("福利")) {
                    AnimationImageActivity.start(this@GankDayActivity, view!!, list!![position].url)
                } else if ((!item?.type.equals("福利")) && (!item?.type.equals("title"))) {
                    startActivity<WebActivity>("url" to list?.get(position)?.url.toString())
                }
            }

            override fun onItemLongClick(view: View?, holder: RecyclerView.ViewHolder?, position: Int): Boolean {
                return false
            }
        })
    }

    override fun generateLayoutId(): Int {
        return R.layout.activity_gank_day
    }

    override fun generatePresenter(): GankDayPresenter {
        return GankDayPresenter( this)
    }


    fun getGankDayData(date: String = formatDate()) {
        mPresenter?.requestGankDayData(date)
    }

    fun formatDate(): String {
        var format: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd");
        return format.format(Date())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.gank_day, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show(fragmentManager, "Datepickerdialog")
        return super.onOptionsItemSelected(item)
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int, yearEnd: Int, monthOfYearEnd: Int, dayOfMonthEnd: Int) {
        list?.clear()
        getGankDayData(year.toString() + "/" + (monthOfYear + 1).toString() + "/" + dayOfMonth.toString())
    }
}
