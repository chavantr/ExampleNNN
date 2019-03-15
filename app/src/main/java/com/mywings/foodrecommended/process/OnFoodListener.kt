package com.mywings.foodrecommended.process

import org.json.JSONArray

interface OnFoodListener {
    fun onFoodSuccess(result: JSONArray)
}