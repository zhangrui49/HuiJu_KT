package com.zhangrui.huiju.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zhangrui.huiju.R
import com.zhangrui.huiju.bean.GankData
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import org.jetbrains.anko.ctx


/**
 * Created by zhangrui on 2017/7/14.
 */
class GankAdapter(context: Context, layout: Int, list: ArrayList<GankData>) : CommonAdapter<GankData>(context, layout, list) {
    var context: Context? = null;
    var list: ArrayList<GankData>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: GankData?, position: Int) {
        Glide.with(context)
                .load(list!![position].url)
                .into(holder!!.getView(R.id.image));
    }

}