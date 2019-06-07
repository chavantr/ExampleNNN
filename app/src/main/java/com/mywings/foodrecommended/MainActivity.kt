package com.mywings.foodrecommended

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mywings.foodrecommended.models.User
import com.mywings.foodrecommended.process.*
import kotlinx.android.synthetic.main.activity_main.*

import org.json.JSONObject

class MainActivity : AppCompatActivity(), OnLoginListener {


    private lateinit var progressDialogUtil: ProgressDialogUtil
    private var extraC: Int = 0
    private var breakfast: Double = 0.0
    private var lunch: Double = 0.0
    private var dinner: Double = 0.0
    private var snack: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialogUtil = ProgressDialogUtil(this)
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnForgotPassword.setOnClickListener {
            val intent = Intent(this@MainActivity, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (txtUserName.text!!.isEmpty() && txtPassword.text!!.isEmpty()) {
                Snackbar.make(btnSignUp, "All fields required.", Snackbar.LENGTH_LONG).show()
            } else {
                initLogin()
            }
        }

    }


    private fun calculatePercentage(type: String?): Double {
        return when (type) {
            "1" -> UserInfoHolder.getInstance().user.extraCal * getAdded(UserInfoHolder.getInstance().user.extraCal, 20)
            "2" -> 0.0
            "3" -> 0.0
            "4" -> 0.0
            else -> 0.0
        }
    }

    private fun getAdded(value: Double?, per: Int): Double {
        //DETERMINISTIC
        return when ("") {
            generate() -> (value!! * per / 100)
            generate() -> value!! * per / 100
            generate() -> value!! * per / 100
            generate() -> value!! * per / 100
            else -> 0.0
        }
    }

    private fun generate(): String {
        return when {
            calculateBMI() < 18.5 -> "Underweight"
            calculateBMI() in 18.5..24.9 -> "Healthy"
            calculateBMI() in 25.0..29.9 -> "Overweight"
            calculateBMI() > 30.0 -> "Obese"
            else -> ""
        }
    }

    private fun calculateBMI(): Double {
        val cmTom = UserInfoHolder.getInstance().user.height.toDouble() / 100
        val value = UserInfoHolder.getInstance().user.weight.toDouble() / (cmTom * cmTom)
        return value
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


    override fun onLoginSuccess(result: JSONObject?) {


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

            user.category = result.getInt("Category")

            user.extraCal = calculateCal(user.weight.toInt(), user.exercise)

            UserInfoHolder.getInstance().user = user

            when (generate()) {
                "Underweight" -> {
                    val plusU = user.extraCal * 20 / 100
                    user.extraCal = user.extraCal + plusU
                }
                "Healthy" -> {

                }
                "Overweight" -> {
                    val plusOV = user.extraCal * 20 / 100
                    user.extraCal = user.extraCal - plusOV
                }
                "Obese" -> {
                    val plusO = user.extraCal * 25 / 100
                    user.extraCal = user.extraCal - plusO
                }
            }

            breakfast = user.extraCal * 25 / 100
            lunch = user.extraCal * 30 / 100
            dinner = user.extraCal * 30 / 100
            snack = user.extraCal * 15 / 100

            com.mywings.foodrecommended.models.UserInfoHolder.getInstance().breakfast = breakfast
            com.mywings.foodrecommended.models.UserInfoHolder.getInstance().lunch = lunch
            com.mywings.foodrecommended.models.UserInfoHolder.getInstance().dinner = dinner
            com.mywings.foodrecommended.models.UserInfoHolder.getInstance().snacks = snack


            val intent = Intent(this@MainActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this@MainActivity, "Enter valid username and password", Toast.LENGTH_LONG).show()
        }


    }

    private fun calculateCal(weight: Int, activity: String?): Double =
        weight * ConstantsUtil.VALUE * EXTRA * calculateExe(activity)

    private fun calculateExe(exe: String?): Double {
        return when (exe) {
            "Sedentary" -> 1.1
            "Lightly active" -> 1.3
            "Moderately active" -> 1.6
            else -> 1.1
        }
    }

    companion object {
        const val EXTRA = 10

    }
}
