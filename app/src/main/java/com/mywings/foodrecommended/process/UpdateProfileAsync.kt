package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONObject

class UpdateProfileAsync : AsyncTask<JSONObject, Void, String>() {

    private val httpConnectionUtil = HttpConnectionUtil()

    private lateinit var onUpdateProfileListener: OnUpdateProfileListener

    override fun doInBackground(vararg params: JSONObject?): String {
        return httpConnectionUtil.requestPost(ConstantsUtil.URL + ConstantsUtil.UPDATE_PROFILE, params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onUpdateProfileListener.onUpdateProfileSuccess(result!!)
    }

    fun setOnUpdateProfileListener(onUpdateProfileListener: OnUpdateProfileListener, request: JSONObject) {
        this.onUpdateProfileListener = onUpdateProfileListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }
}