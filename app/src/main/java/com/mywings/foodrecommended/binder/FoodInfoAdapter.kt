package com.mywings.foodrecommended.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mywings.foodrecommended.R
import com.mywings.foodrecommended.models.Calories
import com.mywings.foodrecommended.process.OnFoodItemSelectedListener
import kotlinx.android.synthetic.main.layout_food_info_row.view.*

class FoodInfoAdapter(lst: List<Calories>?, flg: Boolean) : RecyclerView.Adapter<FoodInfoAdapter.FoodInfoViewHolder>() {

    var lstFoodInfo = lst
    var flag = flg

    private lateinit var onFoodItemSelectedListener: OnFoodItemSelectedListener

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): FoodInfoViewHolder {
        return FoodInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_food_info_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = lstFoodInfo!!.size

    override fun onBindViewHolder(viewHolder: FoodInfoViewHolder, position: Int) {

        viewHolder.lblFoodInfo.text = lstFoodInfo!![position]!!.name

        viewHolder.lblFoodInfo.setOnClickListener {
            if (flag)
                onFoodItemSelectedListener.onFoodSelectedSuccess(lstFoodInfo!![position])
        }
    }

    fun setOnFoodInfoListener(onFoodItemSelectedListener: OnFoodItemSelectedListener) {
        this.onFoodItemSelectedListener = onFoodItemSelectedListener
    }

    inner class FoodInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblFoodInfo = itemView.lblInfo

    }

}