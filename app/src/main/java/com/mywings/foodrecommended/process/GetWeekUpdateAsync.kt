package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONArray

class GetWeekUpdateAsync : AsyncTask<Int, Void, List<Int>?>() {

    private lateinit var onWeekUpdateListener: OnWeekUpdateListener

    override fun doInBackground(vararg params: Int?): List<Int>? {
        val response =
            HttpConnectionUtil().requestGet(ConstantsUtil.URL + ConstantsUtil.GET_WEEK_WEIGHT + "?id=" + params[0])
        return if (response.isNotEmpty()) {
            var lst = ArrayList<Int>()
            val jNodes = JSONArray(response)
            if (null != jNodes) {
                for (i in 0..(jNodes.length() - 1)) {
                    val jNode = jNodes.getJSONObject(i)
                    lst.add(jNode.getInt("Weight"))
                }
            }
            lst
        } else {
            null
        }

    }

    override fun onPostExecute(result: List<Int>?) {
        super.onPostExecute(result)
        onWeekUpdateListener.onWeekUpdateSuccess(result)
    }

    fun setOnWeekUpdateListener(onWeekUpdateListener: OnWeekUpdateListener, request: Int?) {
        this.onWeekUpdateListener = onWeekUpdateListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }


}