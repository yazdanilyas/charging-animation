package com.example.charginganimations.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.charginganimations.ui.activities.plugin.ChargingActivity

class PowerConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "connected", Toast.LENGTH_LONG).show()
                val powerConnectionIntent = Intent(context, ChargingActivity::class.java)
                powerConnectionIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context?.startActivity(powerConnectionIntent)

            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, "desconnedt", Toast.LENGTH_LONG).show()
                val localBroadcastManager = context?.let { LocalBroadcastManager.getInstance(it) }
                localBroadcastManager?.sendBroadcast(Intent("ACTION_FINISH"))
            }
        }

    }
}