package com.mywings.foodrecommended.process

import android.os.AsyncTask

class ExpertAsync : AsyncTask<String?, Void, String?>() {

    private lateinit var onExpertListener: OnExpertListener

    override fun doInBackground(vararg params: String?): String? {
        return HttpConnectionUtil().requestGet(ConstantsUtil.URL + ConstantsUtil.EXPERT_COMMENT + params[0])
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        onExpertListener.onExpertSuccess(result)
    }

    fun setOnExpertCommentListener(onExpertListener: OnExpertListener, comment: String?) {
        this.onExpertListener = onExpertListener
        super.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, comment)
    }

}