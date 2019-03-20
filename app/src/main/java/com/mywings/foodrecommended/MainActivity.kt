package com.mywings.foodrecommended

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.mywings.foodrecommended.models.User
import com.mywings.foodrecommended.process.LoginAsync
import com.mywings.foodrecommended.process.OnLoginListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import com.mywings.foodrecommended.process.UserInfoHolder
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONObject

class MainActivity : AppCompatActivity(), OnLoginListener {


    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialogUtil = ProgressDialogUtil(this)
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {

            //txtUserName.setText("xyz")
            //txtPassword.setText("xyz")

            if (txtUserName.text!!.isEmpty() && txtPassword.text!!.isEmpty()) {
                Snackbar.make(btnSignUp, "All fields required.", Snackbar.LENGTH_LONG).show()
            } else {
                initLogin()

            }

        }
    }

    private fun initLogin() {
        progressDialogUtil.show()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        request.put("login", param)
        val loginAsync = LoginAsync()
        loginAsync.setOnLoginListener(this, request)
    }


    override fun onLoginSuccess(result: JSONObject) {


        progressDialogUtil.hide()

        if (null != result) {

            var user = User()

            user.id = result.getInt("Id")

            user.name = result.getString("Name")

            user.username = result.getString("Username")

            user.password = result.getString("Password")

            user.age = result.getString("Age")

            user.allergy = result.getString("Allergy")

            user.medicine = result.getString("Medicine")

            user.predesea = result.getString("PrevDes")

            user.gender = result.getString("Gender")

            user.state = result.getString("State")

            user.season = result.getString("Season")

            user.height = result.getString("Height")

            user.weight = result.getString("Weight")

            user.exercise = result.getString("Exercise")

            user.calories = result.getString("Calleries")

            user.idealWeight = result.getString("IdealWeight")

            UserInfoHolder.getInstance().user = user
            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
        }


    }
}
