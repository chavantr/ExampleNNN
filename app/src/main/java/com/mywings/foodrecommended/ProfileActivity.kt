package com.mywings.foodrecommended

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.mywings.foodrecommended.process.OnUpdateProfileListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import com.mywings.foodrecommended.process.UpdateProfileAsync
import com.mywings.foodrecommended.process.UserInfoHolder
import kotlinx.android.synthetic.main.activity_profile.*

import org.json.JSONObject

class ProfileActivity : AppCompatActivity(), OnUpdateProfileListener {

    private lateinit var progressDialogUtil: ProgressDialogUtil

    // private lateinit var array: Array<String>

    private lateinit var arrayE: Array<String>

    //  private lateinit var arrayES: Array<String>

    private val user = UserInfoHolder.getInstance().user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        txtName.setText(user.name)
        txtUserName.setText(user.username)
        txtPassword.setText(user.password)
        txtAge.setText(user.age)
        txtWeight.setText(user.weight)
        txtHeight.setText(user.height)

        if (txtWeight.text.toString().isNotEmpty() && txtHeight.text.toString().isNotEmpty()) {
            lblBMI.text = "BMI : ${to2Digit()} ${generate()} "
        }

        arrayE = resources.getStringArray(R.array.exercise)

        when (user.gender) {
            "Select" -> {
                spnGender.setSelection(0)
            }
            "Male" -> {
                spnGender.setSelection(1)
            }
            "Female" -> {
                spnGender.setSelection(2)
            }
        }


        var ij = 0

        for (i in 0..arrayE.size) {
            if (arrayE[i].equals(user.exercise, true)) {
                ij = i
                break
            }
        }

        spnExercise.setSelection(ij)


        progressDialogUtil = ProgressDialogUtil(this)

        txtWeight.addTextChangedListener(height)

        btnCancel.setOnClickListener {
            finish()
        }

        btnRegister.setOnClickListener {
            if (validate()) {
                initUpdateProfile()
            } else {
                var item = Snackbar.make(btnRegister, "All fields required.", Snackbar.LENGTH_LONG)
                item.show()
            }
        }
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

    private fun initUpdateProfile() {
        progressDialogUtil.show()
        var request = JSONObject()
        var param = JSONObject()
        param.put("Id", user.id)
        param.put("Name", txtName.text)
        param.put("Username", txtUserName.text)
        param.put("Password", txtPassword.text)
        param.put("Age", txtAge.text)
        param.put("Gender", spnGender.selectedItem.toString())
        param.put("Height", txtHeight.text)
        param.put("Weight", txtWeight.text)
        param.put("Exercise", spnExercise.selectedItem.toString())
        request.put("user", param)
        val updateProfileAsync = UpdateProfileAsync()
        updateProfileAsync.setOnUpdateProfileListener(this, request)
    }

    override fun onUpdateProfileSuccess(result: String) {
        progressDialogUtil.hide()
        if (result.isNotEmpty()) {

            var item = Snackbar.make(btnRegister, "Profile updated.", Snackbar.LENGTH_INDEFINITE)

            item.setAction("Ok") {
                finish()
            }

            item.show()

        } else {

            var item = Snackbar.make(btnRegister, "An error has occurred, try later", Snackbar.LENGTH_LONG)

            item.setAction("Ok") {

            }

            item.show()

        }
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
}
