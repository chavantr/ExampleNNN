package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONObject

class SaveFoodAsync : AsyncTask<JSONObject, Void, String?>() {

    private lateinit var onFoodSaveListener: OnFoodSaveListener

    override fun doInBackground(vararg params: JSONObject?): String? {
        return HttpConnectionUtil().requestPost(ConstantsUtil.URL + ConstantsUtil.FOOD_SAVE, params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onFoodSaveListener.onFoodSaveSuccess(result)
    }

    fun setOnFoodSaveListener(onFoodSaveListener: OnFoodSaveListener, request: JSONObject) {
        this.onFoodSaveListener = onFoodSaveListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }


}