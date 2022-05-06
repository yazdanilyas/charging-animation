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
        unregisterReceiver(battery_receiver)
    }

    private fun registerBatteryLevelReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(battery_receiver, filter)
    }

    private val battery_receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val isPresent = intent.getBooleanExtra("present", false)
            val technology = intent.getStringExtra("technology")
            val plugged = intent.getIntExtra("plugged", -1)
            val scale = intent.getIntExtra("scale", -1)
            val health = intent.getIntExtra("health", 0)
            val status = intent.getIntExtra("status", 0)
            val rawlevel = intent.getIntExtra("level", -1)
            val voltage = intent.getIntExtra("voltage", 0)
            val temperature = intent.getIntExtra("temperature", 0)
            var level = 0
            val bundle = intent.extras
            Log.i("BatteryLevel", bundle.toString())
            if (isPresent) {
                if (rawlevel >= 0 && scale > 0) {
                    level = rawlevel * 100 / scale
                }
                var info = "Battery Level: $level%\n"
                info += "Technology: $technology\n"
                info += """
                Plugged: ${getPlugTypeString(plugged).toString()}
                
                """.trimIndent()
                info += """
                Health: ${getHealthString(health).toString()}
                
                """.trimIndent()
                info += """
                Status: ${getStatusString(status).toString()}
                
                """.trimIndent()
                info += "Voltage: $voltage\n"
                info += "Temperature: $temperature\n"
                setBatteryLevelText(
                    """
                    $info
                    
                    ${bundle.toString()}
                    """.trimIndent()
                )
            } else {
                setBatteryLevelText("Battery not present!!!")
            }
        }
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

    private fun setBatteryLevelText(text: String) {
        mBinding.infoTv.append("" + text)
    }
}