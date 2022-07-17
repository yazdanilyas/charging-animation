package com.example.charginganimations.ui.activities.plugin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.charginganimations.databinding.ActivityChargingBinding
import com.example.charginganimations.utils.AppUtils
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.PrefUtils

class ChargingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityChargingBinding
    private val activityFinishReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if ("ACTION_FINISH" == intent?.action) {
                finish()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChargingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setStatusBarAndBackgroundColor()
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        val filter = IntentFilter()
        filter.addAction("ACTION_FINISH")
        localBroadcastManager.registerReceiver(activityFinishReceiver, filter)
        showAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(activityFinishReceiver)
    }

    private fun showAnimation() {
        val animGif = PrefUtils.getInt(this@ChargingActivity, CommonKeys.KEY_ANIM_GIF)
        mBinding.animationView.setAnimation(animGif)
//        Glide.with(this).asGif().load(animGif).into(mBinding.animationImg)
    }


    private fun setStatusBarAndBackgroundColor() {
        val selectedColor = PrefUtils.getInt(this@ChargingActivity, CommonKeys.KEY_SELECTED_COLOR)
        Log.d("TAG", "setStatusBarAndBackgroundColor: $selectedColor")
        AppUtils.setStatusBarColor(this, selectedColor)
        mBinding.root.setBackgroundColor(selectedColor)
    }
}