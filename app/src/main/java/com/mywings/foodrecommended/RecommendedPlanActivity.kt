package com.mywings.foodrecommended

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mywings.foodrecommended.models.SaveFood
import com.mywings.foodrecommended.models.UserInfoHolder
import com.mywings.foodrecommended.process.*
import kotlinx.android.synthetic.main.activity_recommended_plan.*
import org.json.JSONObject

class RecommendedPlanActivity : AppCompatActivity(), OnFoodSaveListener, OnGetSaveFoodListener {

    private lateinit var type: String
    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended_plan)

        lblSnacks.setOnClickListener {
            type = "3"
            intent("3", UserInfoHolder.getInstance().snacks)
        }

        lblTimeForBreakfast.setOnClickListener {
            type = "1"
            intent("1", UserInfoHolder.getInstance().breakfast)
        }

        lblTimeForLunch.setOnClickListener {
            type = "2"
            intent("2", UserInfoHolder.getInstance().lunch)
        }

        lblTimeForDinner.setOnClickListener {
            type = "4"
            intent("4", UserInfoHolder.getInstance().dinner)
        }

        btnSave.setOnClickListener {
            init()
        }

        progressDialogUtil = ProgressDialogUtil(this)

        initFood()

    }

    private fun init() {
        progressDialogUtil.show()
        val saveFoodAsync = SaveFoodAsync()
        val jRequest = JSONObject()
        val param = JSONObject()
        param.put("Breakfast", lblTimeForBreakfast.text)
        param.put("Lunch", lblTimeForLunch.text)
        param.put("Snacks", lblSnacks.text)
        param.put("Dinner", lblTimeForDinner.text)
        param.put("UId", com.mywings.foodrecommended.process.UserInfoHolder.getInstance().user.id)
        jRequest.put("request", param)
        saveFoodAsync.setOnFoodSaveListener(this, jRequest)
    }

    private fun initFood() {
        progressDialogUtil.show()
        val getSaveFoodAsync = GetSaveFoodAsync()
        getSaveFoodAsync.setOnGetSaveFoodListener(
            this,
            "?id=" + com.mywings.foodrecommended.process.UserInfoHolder.getInstance().user.id
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (RESULT_OK == resultCode) {
            if (requestCode == 1001) {
                when (type) {
                    "1" -> {
                        lblTimeForBreakfast.text = UserInfoHolder.getInstance().calories.name
                    }
                    "2" -> {
                        lblTimeForLunch.text = UserInfoHolder.getInstance().calories.name
                    }
                    "3" -> {
                        lblSnacks.text = UserInfoHolder.getInstance().calories.name
                    }
                    "4" -> {
                        lblTimeForDinner.text = UserInfoHolder.getInstance().calories.name
                    }
                }
            }
        }
    }

    private fun intent(type: String?, calories: Double?) {
        val intent = Intent(this, SelectFoodActivity::class.java)
        intent.putExtra("type", type)
        intent.putExtra("calories", calories)
        startActivityForResult(intent, 1001)
    }

    override fun onGetSaveFoodSuccess(result: SaveFood?) {
        progressDialogUtil.hide()
        if (null != result) {
            lblTimeForBreakfast.text = result.breakfast
            lblTimeForLunch.text = result.lunch
            lblSnacks.text = result.snacks
            lblTimeForDinner.text = result.dinner
        }
    }

    override fun onFoodSaveSuccess(result: String?) {
        progressDialogUtil.hide()
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
        finish()
    }


}
