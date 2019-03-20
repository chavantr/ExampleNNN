package com.mywings.foodrecommended.process

import android.os.AsyncTask

class UpdateWeeklyWeightAsync : AsyncTask<String?, Void, String?>() {

    private lateinit var onSetOnUpdateWeekWeightListener: OnSetOnUpdateWeekWeightListener

    override fun doInBackground(vararg params: String?): String? {
        return HttpConnectionUtil().requestGet(ConstantsUtil.URL + ConstantsUtil.UPDATE_WEEK_WEIGHT + params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onSetOnUpdateWeekWeightListener.onSetUpdateSuccess(result)
    }

    fun setOnUpdateWeekListener(onSetOnUpdateWeekWeightListener: OnSetOnUpdateWeekWeightListener, request: String?) {
        this.onSetOnUpdateWeekWeightListener = onSetOnUpdateWeekWeightListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }


}