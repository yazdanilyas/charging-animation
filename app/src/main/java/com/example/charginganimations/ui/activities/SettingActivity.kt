package com.example.charginganimations.ui.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.R
import com.example.charginganimations.databinding.ActivitySettingBinding
import com.example.charginganimations.services.ChargingService
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.Constants
import com.example.charginganimations.utils.PrefUtils

class SettingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setListeners()
        setSavedSettings()
        setBatterySliderPosition()
//        setBatterySliderListener()
    }

    private fun setSavedSettings() {
        val closingMethod = PrefUtils.getInt(this@SettingActivity, CommonKeys.CLOSING_METHOD)
        if (closingMethod == 0) {
            mBinding.closeMethodDescTv.text = getString(R.string.single_tap)
        } else {
            mBinding.closeMethodDescTv.text = getString(R.string.double_tap)
        }

        when (PrefUtils.getLong(this@SettingActivity, CommonKeys.KEY_ANIM_DURATION)) {
            10000L -> {
                mBinding.durationTv.text = getString(R.string.ten_seconds)
            }
            20000L -> {
                mBinding.durationTv.text = getString(R.string.twenty_seconds)
            }
            30000L -> {
                mBinding.durationTv.text = getString(R.string.thirty_seconds)
            }
            60000L -> {
                mBinding.durationTv.text = getString(R.string._1_minute)
            }
            else -> {
                mBinding.durationTv.text = getString(R.string.always)
            }
        }
    }

    private fun setListeners() {
        mBinding.serviceSwitch.setOnCheckedChangeListener { switchButtonView, isChecked ->
            startStopAnimationService(switchButtonView, isChecked)
        }
        mBinding.closeMethodImg.setOnClickListener {
            closingMethodPopUp(it)
        }
        mBinding.durationArrowImg.setOnClickListener {
            animationDurationPopUp(it)
        }
    }

    private fun animationDurationPopUp(view: View) {
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_animation_duration, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.tenSecond -> {
                        PrefUtils.setLong(
                            this@SettingActivity, CommonKeys.KEY_ANIM_DURATION,
                            10000
                        )
                        mBinding.durationTv.text = getString(R.string.ten_seconds)
                    }
                    R.id.twentySecond -> {
                        PrefUtils.setLong(
                            this@SettingActivity, CommonKeys.KEY_ANIM_DURATION,
                            20000
                        )
                        mBinding.durationTv.text = getString(R.string.twenty_seconds)
                    }
                    R.id.thirtySecond -> {
                        PrefUtils.setLong(
                            this@SettingActivity, CommonKeys.KEY_ANIM_DURATION,
                            30000
                        )
                        mBinding.durationTv.text = getString(R.string.thirty_seconds)
                    }
                    R.id.oneMinute -> {
                        PrefUtils.setLong(
                            this@SettingActivity, CommonKeys.KEY_ANIM_DURATION,
                            60000
                        )
                        mBinding.durationTv.text = getString(R.string._1_minute)
                    }
                    R.id.always -> {
                        PrefUtils.setLong(
                            this@SettingActivity, CommonKeys.KEY_ANIM_DURATION, 70000
                        )
                        mBinding.durationTv.text = getString(R.string.always)
                    }
                }
                return true
            }

        })
        popupMenu.show()
    }

    private fun closingMethodPopUp(view: View) {
        val popupMenu = PopupMenu(applicationContext, view)
        popupMenu.menuInflater.inflate(R.menu.menu_closing_method, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
            PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.singleTap -> {
                        PrefUtils.setInt(
                            this@SettingActivity,
                            CommonKeys.CLOSING_METHOD,
                            Constants.SINGLE_TAP
                        )
                        mBinding.closeMethodDescTv.text = getString(R.string.single_tap)
                    }
                    R.id.doubleTap -> {
                        PrefUtils.setInt(
                            this@SettingActivity,
                            CommonKeys.CLOSING_METHOD,
                            Constants.DOUBLE_TAP
                        )
                        mBinding.closeMethodDescTv.text = getString(R.string.double_tap)
                    }
                }
                return true
            }

        })
        popupMenu.show()
    }

    private fun startStopAnimationService(switchButtonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            val intent = Intent(applicationContext, ChargingService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
        } else {
            val intent = Intent(applicationContext, ChargingService::class.java)
            intent.action = Constants.ACTION_KILL
            stopService(intent)
        }
    }


    private fun setBatterySliderPosition() {
        val batteryLevel = PrefUtils.getInt(this, CommonKeys.KEY_BATTERY_LEVEL)

//        mBinding.batteryLevelSlider.value = batteryLevel.toFloat() ?: 10.0f

//        // setting battery slider position
//        when (batteryLevel.toFloat()) {
//            10.0f -> {
//                mBinding.targetImg.setImageResource(R.drawable.ic_target_small)
//            }
//            20.0f -> {
//                mBinding.targetImg.setImageResource(R.drawable.ic_target_medium)
//            }
//            30.0f -> {
//                mBinding.targetImg.setImageResource(R.drawable.ic_target_large)
//            }
//        }

    }

    private fun setBatterySliderListener() {
//        mBinding.batteryLevelSlider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
//            PrefUtils.setInt(this, CommonKeys.KEY_BATTERY_LEVEL, value.toInt())
//        })

    }
}