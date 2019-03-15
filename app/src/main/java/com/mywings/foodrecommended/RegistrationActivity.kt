package com.mywings.foodrecommended

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.mywings.foodrecommended.process.OnRegistrationListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import com.mywings.foodrecommended.process.RegistrationAsync
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity(), OnRegistrationListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    private var strbmi: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        progressDialogUtil = ProgressDialogUtil(this)
        btnCancel.setOnClickListener {
            finish()
        }
        btnRegister.setOnClickListener {
            if (validate()) {
                initRegistration()
            } else {
                Snackbar.make(btnRegister, "All fields required.", Snackbar.LENGTH_LONG).show()
            }
        }

        txtWeight.addTextChangedListener(height)
    }

    private fun to2Digit(): Double {
        return String.format("%.2f", calculateBMI()).toDouble()
    }

    private val height = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (editable.toString().isNotEmpty() && txtHeight.text.toString().isNotEmpty()) {
                lblBMI.text = "BMI : ${to2Digit()} ${generate()} "
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }

    private fun validate(): Boolean {
        if (txtName.text!!.isEmpty() && txtAge.text!!.isEmpty()
            && txtUserName.text!!.isEmpty()
            && txtPassword.text!!.isEmpty()
        ) {
            return false
        }
        return true
    }


    private fun calculateBMI(): Double {
        val cmTom = txtHeight.text.toString().toDouble() / 100
        val value = txtWeight.text.toString().toDouble() / (cmTom * cmTom)
        return value
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

    private fun initRegistration() {

        progressDialogUtil.show()

        val registrationAsync = RegistrationAsync();

        var jrequest = JSONObject()

        var param = JSONObject()

        param.put("Name", txtName.text)
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        param.put("Age", txtAge.text)
        //param.put("Allergy", txtAllergy.text)
        //param.put("Medicine", txtMedicines.text)
        param.put("Gender", spnGender.selectedItem.toString())
        //param.put("State", spnState.selectedItem.toString())
        //param.put("Season", spnSeason.selectedItem.toString())
        param.put("Height", txtHeight.text)
        param.put("Weight", txtWeight.text)
        //param.put("Income", txtEconomic.text)
        param.put("Exercise", spnExercise.selectedItem.toString())
        param.put("Calleries", calculateBMI())
        jrequest.put("user", param)
        registrationAsync.setOnRegistrationListener(this, jrequest)
    }

    override fun onRegistrationSuccess(result: String) {
        progressDialogUtil.hide()
        if (result.isNotEmpty()) {
            val snackbar = Snackbar.make(btnRegister, "Registration completed.", Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Ok") {
                finish()
            }
            snackbar.show()
        } else {
            val snackbar = Snackbar.make(btnRegister, "An error has occurred.try later", Snackbar.LENGTH_LONG)
            snackbar.setAction("Ok") {

            }
            snackbar.show()
        }
    }
}
