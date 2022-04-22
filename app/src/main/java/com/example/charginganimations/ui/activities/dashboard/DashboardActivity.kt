package com.example.charginganimations.ui.activities.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.databinding.ActivityDashboardBinding
import com.example.charginganimations.ui.activities.animations.AnimationsActivity

class DashboardActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setListeners()
    }

    private fun setListeners() {
        mBinding.animationsCv.setOnClickListener {
            startActivity(Intent(this, AnimationsActivity::class.java))
        }
        mBinding.batteryInfoCv.setOnClickListener { }
        mBinding.alaramCv.setOnClickListener { }
        mBinding.settingsCv.setOnClickListener { }
    }


}