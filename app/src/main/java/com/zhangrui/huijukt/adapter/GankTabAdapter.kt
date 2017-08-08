package com.zhangrui.huijukt.adapter

import android.content.Context
import android.view.LayoutInflater
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.gank.GankData
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * Created by zhangrui on 2017/7/20.
 */
class GankTabAdapter(context: Context, layout: Int, list: ArrayList<GankData>) : CommonAdapter<GankData>(context, layout, list) {

    var context: Context? = null;
    var list: ArrayList<GankData>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: GankData?, position: Int) {
        holder?.setText(R.id.desc, t?.desc)
        holder?.setText(R.id.time, t?.publishedAt)
        holder?.setText(R.id.who, t?.who)
    }


}