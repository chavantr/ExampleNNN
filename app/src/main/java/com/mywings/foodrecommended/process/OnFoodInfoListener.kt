package com.mywings.foodrecommended.process

import com.mywings.foodrecommended.models.Calories

interface OnFoodInfoListener {
    fun onFoodInfoSuccess(result: List<Calories>)
}