package com.mywings.foodrecommended.process

import android.os.AsyncTask
import com.mywings.foodrecommended.models.SaveFood
import org.json.JSONObject

class GetSaveFoodAsync : AsyncTask<String?, Void, SaveFood?>() {


    private lateinit var onGetSaveFoodListener: OnGetSaveFoodListener

    override fun doInBackground(vararg param: String?): SaveFood? {
        val response = HttpConnectionUtil().requestGet(ConstantsUtil.URL + ConstantsUtil.GET_SAVE_FOOD + param[0])
        if (response.isNotEmpty()) {
            val jResponse = JSONObject(response)
            var saveFood = SaveFood()
            saveFood.id = jResponse.getInt("Id")
            saveFood.breakfast = jResponse.getString("Breakfast")
            saveFood.dinner = jResponse.getString("Dinner")
            saveFood.lunch = jResponse.getString("Lunch")
            saveFood.snacks = jResponse.getString("Snacks")
            saveFood.uid = jResponse.getInt("UId")
            return saveFood
        }
        return null
    }

    override fun onPostExecute(result: SaveFood?) {
        super.onPostExecute(result)
        onGetSaveFoodListener.onGetSaveFoodSuccess(result)
    }

    fun setOnGetSaveFoodListener(onGetSaveFoodListener: OnGetSaveFoodListener, request: String?) {
        this.onGetSaveFoodListener = onGetSaveFoodListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }
}