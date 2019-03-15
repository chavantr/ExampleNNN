package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONObject

class UpdatePrice : AsyncTask<JSONObject, Void, String?>() {

    private val httpConnectionUtil = HttpConnectionUtil()

    override fun doInBackground(vararg params: JSONObject?): String? {
        return httpConnectionUtil.requestPost(ConstantsUtil.URL + ConstantsUtil.INSERT_FOOD, params[0])
    }

}