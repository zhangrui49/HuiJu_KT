package com.zhangrui.huijukt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.douban.Works
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder


/**
 * Created by zhangrui on 2017/7/14.
 */
class WorkAdapter(context: Context, layout: Int, list: ArrayList<Works>) : CommonAdapter<Works>(context, layout, list) {
    var context: Context? = null
    var list: ArrayList<Works>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: Works?, position: Int) {
        val image = holder?.getView<ImageView>(R.id.image)
        Glide.with(context)
                .load(list!![position].subject?.images?.large)
                .into(image)
    }

}