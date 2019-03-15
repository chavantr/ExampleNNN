package com.mywings.foodrecommended

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mywings.foodrecommended.process.UpdatePrice
import com.mywings.foodrecommended.process.UserInfoHolder
import kotlinx.android.synthetic.main.activity_food_detail.*
import org.json.JSONObject

class FoodDetailActivity : AppCompatActivity() {

    private val food = UserInfoHolder.getInstance().food
    private val user = UserInfoHolder.getInstance().user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)
        lblName.text = "Food Name : ${food.name}"
        lblPrice.text = "Price : ${food.agegroupfrom} - ${food.pricerangeto}"
        lblAgeGroup.text = "Age group : ${food.agegroupfrom} - ${food.agegroupto}"
        lblSeason.text = "Season : ${food.season}"
        lblState.text = "State : ${food.state}"
        init()
    }

    private fun init() {
        val updatePrice = UpdatePrice()
        var request = JSONObject()
        var param = JSONObject()
        param.put("UId", user.id)
        param.put("Min", if (food.pricerangefrom.isNullOrEmpty()) food.pricerangefrom else 0)
        param.put("Max", if (food.pricerangeto.isNullOrEmpty()) food.pricerangeto else 0)
        param.put("FId", food.id)
        request.put("request", param)
        updatePrice.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request)
    }
}
