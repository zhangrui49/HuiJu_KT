package com.zhangrui.huijukt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.gank.GankData
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder


/**
 * Created by zhangrui on 2017/7/14.
 */
class WelfareAdapter(context: Context, layout: Int, list: ArrayList<GankData>) : CommonAdapter<GankData>(context, layout, list) {
    var context: Context? = null
    var list: ArrayList<GankData>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: GankData?, position: Int) {
        val image = holder?.getView<ImageView>(R.id.image)
//        val lp = image?.layoutParams
//        if (lp is FlexboxLayoutManager.LayoutParams) {
//            lp.flexGrow = 1.0f
//        }
        Glide.with(context)
                .load(list!![position].url?.plus("?imageView2/0/w/100"))
                // .load(list!![position].url)
                .into(image)

    }

}