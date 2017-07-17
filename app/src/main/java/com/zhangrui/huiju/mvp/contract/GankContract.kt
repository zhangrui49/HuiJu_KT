package com.zhangrui.huiju.mvp.contract

import android.content.Context
import com.zhangrui.huiju.base.BaseModel
import com.zhangrui.huiju.base.BasePresenter
import com.zhangrui.huiju.base.BaseView
import com.zhangrui.huiju.bean.Gank
import rx.Observable

/**
 * Created by zhangrui on 2017/7/13.
 */
class GankContract {
    interface View :BaseView{
         fun showData(data:Gank)
    }

    abstract class Presenter(context: Context,view: GankContract.View) : BasePresenter<View>(context,view){

      abstract fun requestData(path:String,pageSize:Int,page:Int);
    }

    interface Model :BaseModel{

    }
}