package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject

class GetFoodRecommendeAsync : AsyncTask<JSONObject, Void, JSONArray>() {

    private val httpConnectionUtil = HttpConnectionUtil()
    private lateinit var onFoodListener: OnFoodListener

    override fun doInBackground(vararg param: JSONObject?): JSONArray {
        return JSONArray(httpConnectionUtil.requestPost(ConstantsUtil.URL + ConstantsUtil.GET_FOOD, param[0]))
    }

    override fun onPostExecute(result: JSONArray?) {
        super.onPostExecute(result)
        onFoodListener.onFoodSuccess(result!!)
    }

    fun setOnFoodListener(onFoodListener: OnFoodListener, request: JSONObject) {
        this.onFoodListener = onFoodListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }
}