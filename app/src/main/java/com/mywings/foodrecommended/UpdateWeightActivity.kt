package com.mywings.foodrecommended

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.mywings.foodrecommended.binder.WeekWeightAdapter
import com.mywings.foodrecommended.process.*
import kotlinx.android.synthetic.main.activity_update_weight.*

class UpdateWeightActivity : AppCompatActivity(), OnWeekUpdateListener, OnSetOnUpdateWeekWeightListener {


    private lateinit var weekWeightAdapter: WeekWeightAdapter
    private lateinit var progressDialogUtil: ProgressDialogUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_weight)
        progressDialogUtil = ProgressDialogUtil(this)
        lstWeekUpdate.layoutManager = LinearLayoutManager(this)
        init()
        btnUpdate.setOnClickListener {
            initUpdate()
        }
    }

    private fun init() {
        progressDialogUtil.show()
        val getWeekUpdateAsync = GetWeekUpdateAsync()
        getWeekUpdateAsync.setOnWeekUpdateListener(this, UserInfoHolder.getInstance().user.id)
    }

    private fun initUpdate() {
        progressDialogUtil.show()
        val updateWeekUpdateAsync = UpdateWeeklyWeightAsync()
        updateWeekUpdateAsync.setOnUpdateWeekListener(
            this,
            "?weight=" + txtWeight.text.toString() + "&uid=" + UserInfoHolder.getInstance().user.id
        )
    }

    override fun onWeekUpdateSuccess(result: List<Int>?) {
        progressDialogUtil.hide()
        if (result!!.isNotEmpty()) {
            weekWeightAdapter = WeekWeightAdapter(result)
            lstWeekUpdate.adapter = weekWeightAdapter
        }
    }

    override fun onSetUpdateSuccess(result: String?) {

        progressDialogUtil.hide()
        txtWeight.setText("")

    }
}
