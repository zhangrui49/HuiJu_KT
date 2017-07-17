package com.zhangrui.huiju.bean

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.json.JSONException
import org.json.JSONObject

import java.util.HashMap

/**
 * DESC:
 * Created by zhangrui on 2016/11/7.
 */

class GankDayData {


    private var mResultList: HashMap<String, List<GankData>>? = null
    var isError: Boolean = false
    var results: DayData? = null
    var category: List<String>? = null

    class DayData


    fun generateResult(): HashMap<*, *> {
        mResultList = HashMap<String, List<GankData>>()
        val gson = Gson()
        for (key in category!!) {
            try {
                val jsonObject = JSONObject(gson.toJson(results))
                mResultList!!.put(key, gson.fromJson<Any>(jsonObject.optString(key), object : TypeToken<List<GankData>>() {

                }.type) as List<GankData>)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
        return mResultList as HashMap<String, List<GankData>>
    }
    //
    //    public HashMap<String, List<GankData>> getResultList() {
    //        return mResultList;
    //    }
    //
    //    public void setResultList(HashMap<String, List<GankData>> resultList) {
    //        mResultList = resultList;
    //    }
}
