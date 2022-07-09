package com.example.charginganimations.ui.activities.batteryinfo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.databinding.ActivityBatteryInfoBinding


class BatteryInfoActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityBatteryInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityBatteryInfoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        registerBatteryLevelReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
    }

    private fun registerBatteryLevelReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    private val batteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
//            val isPresent = intent.getBooleanExtra("present", false)
//            val technology = intent.getStringExtra("technology")
//            val plugged = intent.getIntExtra("plugged", -1)
//            val scale = intent.getIntExtra("scale", -1)
//            val health = intent.getIntExtra("health", 0)
//            val status = intent.getIntExtra("status", 0)
//            val rawlevel = intent.getIntExtra("level", -1)
//            val voltage = intent.getIntExtra("voltage", 0)
//            val temperature = intent.getIntExtra("temperature", 0)

            val isPresent = intent.extras?.getBoolean(BatteryManager.EXTRA_PRESENT, false)
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)
            val icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0)
            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)
            val technology = intent.extras?.getString(BatteryManager.EXTRA_TECHNOLOGY).toString()
            val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10
            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)


            var level = 0
            val bundle = intent.extras
            Log.i("BatteryLevel", bundle.toString())
            if (isPresent == true) {
                if (rawlevel >= 0 && scale > 0) {
                    level = rawlevel * 100 / scale
                }
                setChargingType(plugged)
                setChargingStatus()
                mBinding.batteryProgressBar.progress = level
                mBinding.batteryPercentageTv.text = "${level}%"
                mBinding.temperatureTv.text = temperature.toString()
                mBinding.voltageTv.text = voltage.toString()
                setBatteryHealth()
                mBinding.technologyTv.text = technology.toString()
                mBinding.capacityTv.text = technology.toString()
            }
        }
    }

    private fun setChargingType(plugged: Int) {
        val usbCharge = plugged == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge = plugged == BatteryManager.BATTERY_PLUGGED_AC
        val wireless = plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS
        var chargingType: String? = null
        when {
            usbCharge -> {
                chargingType = "USB"
            }
            acCharge -> {
                chargingType = "AC Power"
            }
            wireless -> {
                chargingType = "Wireless"
            }

        }

        mBinding.chargingTypeTv.text = chargingType
    }

    private fun setChargingStatus() {
        val statusLbl: String = when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            BatteryManager.BATTERY_STATUS_CHARGING -> "charging"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> "discharging"
            BatteryManager.BATTERY_STATUS_FULL -> "battery full"
            BatteryManager.BATTERY_STATUS_UNKNOWN -> "-1"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "discharging"
            else -> "discharging"
        }

    }

    private fun setBatteryHealth() {
        val health = when (intent?.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)) {
            BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
            BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
            BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Over heat"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over voltage"
            BatteryManager.BATTERY_HEALTH_UNKNOWN -> "Unknown"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified failure"
            else -> "Error"
        }
        mBinding.healthTv.text = health
    }

    private fun getPlugTypeString(plugged: Int): String? {
        var plugType = "Unknown"
        when (plugged) {
            BatteryManager.BATTERY_PLUGGED_AC -> plugType = "AC"
            BatteryManager.BATTERY_PLUGGED_USB -> plugType = "USB"
        }
        return plugType
    }

    private fun getHealthString(health: Int): String? {
        var healthString = "Unknown"
        when (health) {
            BatteryManager.BATTERY_HEALTH_DEAD -> healthString = "Dead"
            BatteryManager.BATTERY_HEALTH_GOOD -> healthString = "Good"
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> healthString = "Over Voltage"
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> healthString = "Over Heat"
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> healthString = "Failure"
        }
        return healthString
    }

    private fun getStatusString(status: Int): String? {
        var statusString = "Unknown"
        when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> statusString = "Charging"
            BatteryManager.BATTERY_STATUS_DISCHARGING -> statusString = "Discharging"
            BatteryManager.BATTERY_STATUS_FULL -> statusString = "Full"
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> statusString = "Not Charging"
        }
        return statusString
    }


}