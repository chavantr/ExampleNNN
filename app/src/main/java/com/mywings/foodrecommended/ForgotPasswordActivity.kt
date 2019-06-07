package com.mywings.foodrecommended

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mywings.foodrecommended.process.ForgotPasswordAsync
import com.mywings.foodrecommended.process.OnForgotPasswordListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_forgot_password.*

import org.json.JSONObject

class ForgotPasswordActivity : AppCompatActivity(), OnForgotPasswordListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        progressDialogUtil = ProgressDialogUtil(this)
        btnResetPassword.setOnClickListener {
            if (validate()) {
                init()
            } else {
                Toast.makeText(this, "Fields required", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validate() = txtUserName.text!!.isNotEmpty() && txtPassword.text!!.isNotEmpty()

    private fun init() {
        progressDialogUtil.show()
        val forgotPasswordAsync = ForgotPasswordAsync()
        val jRequest = JSONObject()
        val param = JSONObject()
        param.put("UserName", txtUserName.text)
        param.put("Password", txtPassword.text)
        jRequest.put("request", param)
        forgotPasswordAsync.setOnForgotPasswordListener(this, jRequest)
    }

    override fun onForgotPasswordSuccess(result: String?) {
        progressDialogUtil.hide()
        if (result!!.isNotEmpty()) {
            Toast.makeText(this, "Reset successfully", Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this, "Error occurred", Toast.LENGTH_LONG).show()
        }
    }

}
