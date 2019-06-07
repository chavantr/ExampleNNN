package com.mywings.foodrecommended

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mywings.foodrecommended.binder.FoodInfoAdapter
import com.mywings.foodrecommended.models.Calories
import com.mywings.foodrecommended.models.UserInfoHolder
import com.mywings.foodrecommended.process.GetFoodInfoAsync
import com.mywings.foodrecommended.process.OnFoodInfoListener
import com.mywings.foodrecommended.process.OnFoodItemSelectedListener
import com.mywings.foodrecommended.process.ProgressDialogUtil
import kotlinx.android.synthetic.main.activity_select_food.*

class SelectFoodActivity : AppCompatActivity(), OnFoodInfoListener, OnFoodItemSelectedListener {

    private lateinit var foodInfoAdapter: FoodInfoAdapter
    private lateinit var progressDialogUtil: ProgressDialogUtil
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_food)
        lstFood.layoutManager = LinearLayoutManager(this)
        progressDialogUtil = ProgressDialogUtil(this)
        flag = intent.extras.getDouble("calories") > 0
        init()
    }

    override fun onFoodInfoSuccess(result: List<Calories>) {
        progressDialogUtil.hide()
        if (result.isNotEmpty()) {
            foodInfoAdapter = FoodInfoAdapter(result, flag)
            foodInfoAdapter.setOnFoodInfoListener(this@SelectFoodActivity)
            lstFood.adapter = foodInfoAdapter
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val getFoodInfoAsync = GetFoodInfoAsync()
        getFoodInfoAsync.setOnFoodInfoListener(
            this,
            "?type=" + intent.extras.getString("type") + "&calories=" + intent.extras.getDouble(
                "calories",
                0.0
            ) + "&category=" + com.mywings.foodrecommended.process.UserInfoHolder.getInstance().user.category
        )
    }

    override fun onFoodSelectedSuccess(calories: Calories?) {
        UserInfoHolder.getInstance().calories = calories
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }

}
