package com.mywings.foodrecommended

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_recommended_plan.*

class RecommendedPlanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommended_plan)


        lblSnacks.setOnClickListener {

            intent("1")
        }

        lblTimeForBreakfast.setOnClickListener {
            intent("2")
        }

        lblTimeForLunch.setOnClickListener {
            intent("3")
        }

        lblTimeForDinner.setOnClickListener {
            intent("3")
        }

        btnSave.setOnClickListener {

        }

    }

    private fun intent(type: String?) {
        val intent = Intent(this, SelectFoodActivity::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }
}
