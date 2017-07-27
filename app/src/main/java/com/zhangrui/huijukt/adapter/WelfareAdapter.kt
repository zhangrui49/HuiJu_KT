package com.zhangrui.huijukt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.wingsofts.dragphotoview.DragPhotoView
import com.zhangrui.huijukt.R
import com.zhangrui.huijukt.bean.GankData
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import org.jetbrains.anko.coroutines.experimental.asReference
import uk.co.senab.photoview.PhotoView


/**
 * Created by zhangrui on 2017/7/14.
 */
class WelfareAdapter(context: Context, layout: Int, list: ArrayList<GankData>) : CommonAdapter<GankData>(context, layout, list) {
    var context: Context? = null;
    var list: ArrayList<GankData>? = null
    var inflater: LayoutInflater? = null

    init {
        this.context = context
        this.list = list
        this.inflater = LayoutInflater.from(context)
    }

    override fun convert(holder: ViewHolder?, t: GankData?, position: Int) {
        var image = holder?.getView<ImageView>(R.id.image)
        Glide.with(context)
                .load(list!![position].url?.plus("?imageView2/0/w/100"))
                //  .load(list!![position].url)
                .into(image)
    }

}