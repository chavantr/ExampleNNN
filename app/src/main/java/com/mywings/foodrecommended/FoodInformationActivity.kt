package com.mywings.foodrecommended

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_information.*

class FoodInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_information)


        lblSnacks.setOnClickListener {

            intent("3", 0)
        }

        lblTimeForBreakfast.setOnClickListener {
            intent("1", 0)
        }

        lblTimeForLunch.setOnClickListener {
            intent("2", 0)
        }

        lblTimeForDinner.setOnClickListener {
            intent("4", 0)
        }

    }

    private fun intent(type: String?, calories: Int?) {
        val intent = Intent(this, SelectFoodActivity::class.java)
        intent.putExtra("type", type)
        intent.putExtra("calories", calories)
        startActivity(intent)
    }
}
