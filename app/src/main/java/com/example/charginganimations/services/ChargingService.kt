package com.example.charginganimations.services

import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.charginganimations.R
import com.example.charginganimations.receivers.PowerConnectionReceiver
import com.example.charginganimations.utils.Constants

class ChargingService : Service() {
    private val NOTIFICATION_CHANNEL_ID: String = "chargingChannelId"
    private val powerConnectionReceiver = PowerConnectionReceiver()
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(powerConnectionReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action.equals(Constants.ACTION_KILL)) {
            stopForeground(true)
        } else {
            createNotification()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(powerConnectionReceiver)
    }

    private fun createNotification() {
        val notificationId = 100
        val channelName = "ChargingChannel"
        val notificationChannel: NotificationChannel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_MIN
            )
            val notificationManager =
                (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val stopActionsIntent = Intent(this, ChargingService::class.java)
        stopActionsIntent.action = Constants.ACTION_KILL
        val pStopSelf = PendingIntent.getService(
            this,
            0,
            stopActionsIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setContentTitle("Animation service is running")
            .setContentText("Tap to stop from setting screen.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(0, getString(R.string.stop), pStopSelf)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
//            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        startForeground(notificationId, notification)
    }
}