package com.mywings.foodrecommended.process

import org.json.JSONObject

interface OnLoginListener {
    fun onLoginSuccess(result: JSONObject)
}