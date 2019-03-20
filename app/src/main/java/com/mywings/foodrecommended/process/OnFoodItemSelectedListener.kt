package com.mywings.foodrecommended.process

import com.mywings.foodrecommended.models.Calories

interface OnFoodItemSelectedListener {
    fun onFoodSelectedSuccess(calories: Calories?)
}
