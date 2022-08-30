package com.example.charginganimations.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.charginganimations.ui.activities.plugin.ChargingActivity

class LockScreenReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_SCREEN_ON)) {
//            val powerConnectionIntent = Intent(context, ChargingActivity::class.java)
//            powerConnectionIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            context?.startActivity(powerConnectionIntent)
        } else if (intent?.action.equals(Intent.ACTION_SCREEN_OFF)) {
            val powerConnectionIntent = Intent(context, ChargingActivity::class.java)
            powerConnectionIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(powerConnectionIntent)
            Toast.makeText(context, "screen off", Toast.LENGTH_LONG).show()
        }
    }
}