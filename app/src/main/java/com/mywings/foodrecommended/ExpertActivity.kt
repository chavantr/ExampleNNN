package com.mywings.foodrecommended

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mywings.foodrecommended.process.ExpertAsync
import com.mywings.foodrecommended.process.OnExpertListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import com.mywings.foodrecommended.process.UserInfoHolder
import kotlinx.android.synthetic.main.activity_expert.*

class ExpertActivity : AppCompatActivity(), OnExpertListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expert)
        progressDialogUtil = ProgressDialogUtil(this)
        btnSend.setOnClickListener {
            if (txtComment.text.isNotEmpty()) {
                init()
            } else {
                Toast.makeText(this@ExpertActivity, "Enter comment", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val expertAsync = ExpertAsync()
        expertAsync.setOnExpertCommentListener(this, "?comment=${txtComment.text}&uid=${UserInfoHolder.getInstance().user.id}")
    }

    override fun onExpertSuccess(result: String?) {
        progressDialogUtil.hide()
        Toast.makeText(this@ExpertActivity, "Your concern sent to expert", Toast.LENGTH_LONG).show()
    }
    
}
