package com.mywings.foodrecommended.binder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mywings.foodrecommended.R
import kotlinx.android.synthetic.main.layout_week_weight.view.*

class WeekWeightAdapter(var lst: List<Int>) : RecyclerView.Adapter<WeekWeightAdapter.WeekWeightViewHolder>() {

    var lstWeeks = lst

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): WeekWeightViewHolder {
        return WeekWeightViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_week_weight,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = lstWeeks!!.size

    override fun onBindViewHolder(viewHolder: WeekWeightViewHolder, position: Int) {
        viewHolder.lblName.text = "Your weight ${lstWeeks[position]}  (Week ${position + 1}"
    }

    inner class WeekWeightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lblName = itemView.lblName
    }

}