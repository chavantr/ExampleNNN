package com.mywings.foodrecommended.process

import android.os.AsyncTask
import org.json.JSONObject

class ForgotPasswordAsync : AsyncTask<JSONObject, Void, String?>() {

    private lateinit var onForgotPasswordListener: OnForgotPasswordListener

    override fun doInBackground(vararg param: JSONObject?): String? {
        return HttpConnectionUtil().requestPost(ConstantsUtil.URL + ConstantsUtil.FORGOT_PASSWORD, param[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onForgotPasswordListener.onForgotPasswordSuccess(result)
    }

    fun setOnForgotPasswordListener(onForgotPasswordListener: OnForgotPasswordListener, request: JSONObject) {
        this.onForgotPasswordListener = onForgotPasswordListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }

}