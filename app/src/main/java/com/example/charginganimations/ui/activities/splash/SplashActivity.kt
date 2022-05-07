package com.example.charginganimations.ui.activities.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.databinding.ActivitySplashBinding
import com.example.charginganimations.ui.activities.dashboard.DashboardActivity
import com.example.charginganimations.utils.Constants


class SplashActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${packageName}")
        )
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)

        startActivity(intent)
        showStartButton()
        setListeners()

    }

    private fun setListeners() {
        mBinding.getStartedButton.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    private fun showStartButton() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            mBinding.progressBar.visibility = View.GONE
            mBinding.getStartedButton.visibility = View.VISIBLE
        }
        handler.postDelayed(runnable, Constants.splashDelay)
    }
}