package com.mywings.foodrecommended.process

import com.mywings.foodrecommended.models.SaveFood

interface OnGetSaveFoodListener {
    fun onGetSaveFoodSuccess(result: SaveFood?)
}