package com.mywings.foodrecommended

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import android.widget.TextView
import com.mywings.foodrecommended.notification.NotificationHelper
import kotlinx.android.synthetic.main.activity_setting.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SettingActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()!!
    private lateinit var time: Array<String>
    private var lstControls = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)

        lblGymTimeMor.setOnClickListener {
            TimePickerDialog(
                this@SettingActivity,
                timeSetGymListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this@SettingActivity)
            ).show()


        }

        lblTimeForBreakfast.setOnClickListener {

            TimePickerDialog(
                this@SettingActivity,
                timeSetBreakfastListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this@SettingActivity)
            ).show()

        }

        lblTimeForLunch.setOnClickListener {

            TimePickerDialog(
                this@SettingActivity,
                timeSetLunchListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this@SettingActivity)
            ).show()

        }

        lblTimeForDinner.setOnClickListener {
            TimePickerDialog(
                this@SettingActivity,
                timeSetDinnerListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                DateFormat.is24HourFormat(this@SettingActivity)
            ).show()

        }

        lstControls.add(lblGymTimeMor)
        lstControls.add(lblTimeForBreakfast)
        lstControls.add(lblTimeForLunch)
        lstControls.add(lblTimeForDinner)

        btnSave.setOnClickListener {

            var ii = 0

            for (i in lstControls.indices) {
                if (lstControls.get(i).text.contains(":")) {
                    val node = lstControls.get(i).text.split(":")
                    NotificationHelper.scheduleRepeatingRTCNotification(this@SettingActivity, node[0], node[1], i)
                    NotificationHelper.enableBootReceiver(this@SettingActivity)
                    ii += 1
                }
            }

            if (ii > 0) {
                var snack = Snackbar.make(btnSave, "Saved successfully", Snackbar.LENGTH_INDEFINITE)
                snack.setAction("Ok") {
                    finish()
                }
                snack.show()
            } else {
                var snack = Snackbar.make(btnSave, "Please set time", Snackbar.LENGTH_LONG)
                snack.show()
            }

        }

    }


    private val timeSetGymListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        lblGymTimeMor.text = convertToTime(calendar)
    }

    private val timeSetBreakfastListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        lblTimeForBreakfast.text = convertToTime(calendar)
    }


    private val timeSetLunchListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        lblTimeForLunch.text = convertToTime(calendar)
    }
    private val timeSetDinnerListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        lblTimeForDinner.text = convertToTime(calendar)
    }


    private fun convertToTime(calendar: Calendar) = SimpleDateFormat("HH:mm").format(calendar.time)


}
