package com.example.charginganimations.ui.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.charginganimations.R
import com.example.charginganimations.databinding.FragmentDashboardBinding
import com.example.charginganimations.ui.activities.SettingActivity
import com.example.charginganimations.ui.activities.animations.AnimationsCategoryActivity
import com.example.charginganimations.ui.activities.batteryinfo.BatteryInfoActivity
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.PrefUtils


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private lateinit var mBinding: FragmentDashboardBinding

    private var param1: String? = null
    private var param2: String? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openDrawerView()
        newUserSetting()
        setListeners()
        registerBatteryLevelReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(batteryReceiver)
    }

    private fun setListeners() {
        mBinding.animationsCv.setOnClickListener {
            startActivity(Intent(activity, AnimationsCategoryActivity::class.java))
        }
        mBinding.batteryInfoCv.setOnClickListener {
            startActivity(Intent(activity, BatteryInfoActivity::class.java))
        }
        mBinding.settingsCv.setOnClickListener {
            startActivity(Intent(activity, SettingActivity::class.java))
        }
    }

    private fun openDrawerView() {
        val profileImage = mBinding.root.findViewById<AppCompatImageView>(R.id.openDrawerImg)
        profileImage?.setOnClickListener {
            val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout)
            drawer.openDrawer(Gravity.LEFT)
        }
    }

    private fun newUserSetting() {
        if (!PrefUtils.getBoolean(requireActivity(), CommonKeys.KEY_NEW_USER)) {
            PrefUtils.setBoolean(requireActivity(), CommonKeys.KEY_10_SEC, true)
            // on first use
            PrefUtils.setBoolean(requireActivity(), CommonKeys.KEY_NEW_USER, true)
            PrefUtils.setInt(requireActivity(), CommonKeys.KEY_BATTERY_LEVEL, 50)
        }
    }

    // Battery level receiver
    private fun registerBatteryLevelReceiver() {
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        requireContext().registerReceiver(batteryReceiver, filter)
    }

    private val batteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {

            val isPresent = intent.extras?.getBoolean(BatteryManager.EXTRA_PRESENT, false)
            val rawlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0)

            var level = 0
            val bundle = intent.extras
            Log.i("BatteryLevel", bundle.toString())
            if (isPresent == true) {
                if (rawlevel >= 0 && scale > 0) {
                    level = rawlevel * 100 / scale
                }
                mBinding.waveLoadingView.centerTitle = "$level%"
                mBinding.waveLoadingView.centerTitleColor =
                    requireContext().getColor(R.color.lightGreen)
                mBinding.waveLoadingView.setCenterTitleStrokeColor(requireContext().getColor(R.color.lightGreen))
                mBinding.waveLoadingView.progressValue = level

            }
        }
    }

}