package com.mywings.foodrecommended.process

import android.os.AsyncTask
import com.mywings.foodrecommended.models.Calories
import org.json.JSONArray

class GetFoodInfoAsync : AsyncTask<String, Void, List<Calories>?>() {


    private lateinit var onFoodInfoListener: OnFoodInfoListener

    override fun doInBackground(vararg params: String?): List<Calories>? {
        val response = HttpConnectionUtil().requestGet(ConstantsUtil.URL + ConstantsUtil.GET_FOOD_INFO + params[0])
        if (response.isEmpty()) return null else {
            val jInfo = JSONArray(response)
            var lst = ArrayList<Calories>()
            if (jInfo.length() > 0) {
            }
            return lst
        }
    }

    override fun onPostExecute(result: List<Calories>?) {
        super.onPostExecute(result)
        onFoodInfoListener.onFoodInfoSuccess(result!!)
    }

    fun setOnFoodInfoListener(onFoodInfoListener: OnFoodInfoListener, request: String?) {
        this.onFoodInfoListener = onFoodInfoListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }
}