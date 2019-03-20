package com.mywings.foodrecommended.models

data class SaveFood(
    var id: Int = 0,
    var breakfast: String = "",
    var lunch: String = "",
    var snacks: String = "",
    var dinner: String = "",
    var uid: Int = 0
)