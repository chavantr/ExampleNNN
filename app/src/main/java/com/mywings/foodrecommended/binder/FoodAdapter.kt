package com.mywings.foodrecommended.binder

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mywings.foodrecommended.FoodDetailActivity
import com.mywings.foodrecommended.R
import com.mywings.foodrecommended.models.Food
import com.mywings.foodrecommended.process.UserInfoHolder
import kotlinx.android.synthetic.main.layout_food_node.view.*

class FoodAdapter(var foods: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    var lstFoods: ArrayList<Food> = foods

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): FoodViewHolder {
        return FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_food_node, parent, false))
    }

    override fun getItemCount(): Int = lstFoods.size

    override fun onBindViewHolder(viewHolder: FoodViewHolder, position: Int) {

        viewHolder.lblName.text = "Name : ${lstFoods[position].name}"
        //viewHolder.lblState.text = lstFoods[position].state
        /* viewHolder.lblPriceRange.text =
             "Price : ${lstFoods[position].pricerangefrom} to ${lstFoods[position].pricerangeto}"*/

        viewHolder.lnrContainer.setOnClickListener {
            UserInfoHolder.getInstance().food = lstFoods[position]
            val intent = Intent(it.context, FoodDetailActivity::class.java)
            it.context.startActivity(intent)
        }

    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val lblName = itemView.lblName
        val lblAgeGroup = itemView.lblAgeGroup
        val lblPriceRange = itemView.lblPriceRange
        val lblState = itemView.lblState
        val lnrContainer = itemView.lnrContainer

    }

}