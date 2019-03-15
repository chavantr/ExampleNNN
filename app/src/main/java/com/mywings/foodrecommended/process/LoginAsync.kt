package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONObject

class LoginAsync : AsyncTask<JSONObject, Void, JSONObject>() {


    private val httpConnectionUtil = HttpConnectionUtil()

    private lateinit var onLoginListener: OnLoginListener

    override fun doInBackground(vararg params: JSONObject?): JSONObject {

        val response = httpConnectionUtil.requestPost(ConstantsUtil.URL + ConstantsUtil.LOGIN, params[0])

        return JSONObject(response)

    }

    override fun onPostExecute(result: JSONObject?) {
        super.onPostExecute(result)
        onLoginListener.onLoginSuccess(result!!)
    }

    fun setOnLoginListener(onLoginListener: OnLoginListener, request: JSONObject) {
        this.onLoginListener = onLoginListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }

}