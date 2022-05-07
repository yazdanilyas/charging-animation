package com.example.charginganimations.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.R
import com.example.charginganimations.databinding.ActivitySettingBinding
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.PrefUtils
import com.google.android.material.slider.Slider

class SettingActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setRadioButtonSate()
        setBatterySliderPosition()
        setRadioListeners()
        setBatterySliderListener()
    }

    private fun setRadioButtonSate() {
        val tenSec = PrefUtils.getBoolean(this, CommonKeys.KEY_10_SEC)
        val twentySec = PrefUtils.getBoolean(this, CommonKeys.KEY_20_SEC)
        val thirtySec = PrefUtils.getBoolean(this, CommonKeys.KEY_30_SEC)
        val oneMin = PrefUtils.getBoolean(this, CommonKeys.KEY_1_MIN)
        val always = PrefUtils.getBoolean(this, CommonKeys.KEY_ALWAYS)
        if (tenSec) {
            mBinding.tenSecRb.isChecked = true
        } else if (twentySec) {
            mBinding.twentySecRb.isChecked = true

        } else if (thirtySec) {
            mBinding.thirtySecRb.isChecked = true

        } else if (oneMin) {
            mBinding.oneMinRb.isChecked = true

        } else if (always) {
            mBinding.alwaysRb.isChecked = true

        }
    }

    private fun setRadioListeners() {

        mBinding.durationRG.setOnCheckedChangeListener { p0, checkedId ->
            when (checkedId) {
                R.id.tenSecRb -> {
                    saveRadioStates(R.id.tenSecRb)
                }
                R.id.twentySecRb -> {
                    saveRadioStates(R.id.twentySecRb)
                }
                R.id.thirtySecRb -> {
                    Log.d("TAGG", "setRadioListeners:")
                    saveRadioStates(R.id.thirtySecRb)

                }
                R.id.oneMinRb -> {
                    Log.d("TAGG", "setRadioListeners:")
                    saveRadioStates(R.id.oneMinRb)

                }
                R.id.alwaysRb -> {
                    Log.d("TAGG", "setRadioListeners:")
                    saveRadioStates(R.id.alwaysRb)
                }
            }
        }

    }

    private fun saveRadioStates(viewId: Int) {
        if (viewId == R.id.tenSecRb) {
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_10_SEC,
                true
            )

            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_20_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_30_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_1_MIN,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_ALWAYS,
                false
            )
        } else if (viewId == R.id.twentySecRb) {
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_10_SEC,
                false
            )

            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_20_SEC,
                true
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_30_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_1_MIN,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_ALWAYS,
                false
            )

        } else if (viewId == R.id.thirtySecRb) {
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_10_SEC,
                false
            )

            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_20_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_30_SEC,
                true
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_1_MIN,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_ALWAYS,
                false
            )

        } else if (viewId == R.id.oneMinRb) {
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_10_SEC,
                false
            )

            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_20_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_30_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_1_MIN,
                true
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_ALWAYS,
                false
            )

        } else if (viewId == R.id.alwaysRb) {
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_10_SEC,
                false
            )

            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_20_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_30_SEC,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_1_MIN,
                false
            )
            PrefUtils.setBoolean(
                this@SettingActivity,
                CommonKeys.KEY_ALWAYS,
                true
            )
        }
    }

    private fun setBatterySliderPosition() {
        val batteryLevel = PrefUtils.getInt(this, CommonKeys.KEY_BATTERY_LEVEL)

        mBinding.batteryLevelSlider.value = batteryLevel.toFloat() ?: 10.0f

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
        mBinding.batteryLevelSlider.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            PrefUtils.setInt(this, CommonKeys.KEY_BATTERY_LEVEL, value.toInt())
        })

    }
}