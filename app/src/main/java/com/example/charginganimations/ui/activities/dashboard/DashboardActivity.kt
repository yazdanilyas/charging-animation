package com.example.charginganimations.ui.activities.dashboard

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.databinding.ActivityDashboardBinding
import com.example.charginganimations.ui.fragments.DashboardFragment
import com.example.charginganimations.utils.CommonUtils

class DashboardActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDashboardBinding
    private val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        addDashboardFragments()
        setListeners()
    }

    private fun addDashboardFragments() {
        fragmentManager.beginTransaction()
            .replace(mBinding.navHostFragmentContentDashboard.id, DashboardFragment()).commit()
    }

    private fun setListeners() {
        mBinding.leftDrawerMenu.rateAppView.setOnClickListener {
            CommonUtils.rateApp(this)
        }
        mBinding.leftDrawerMenu.shareAppView.setOnClickListener {
            CommonUtils.shareApp(this)
        }
        mBinding.leftDrawerMenu.moreAppView.setOnClickListener {
            CommonUtils.moreApp(this)
        }

        mBinding.leftDrawerMenu.policyView.setOnClickListener {
            CommonUtils.openPrivacyPolicy(this@DashboardActivity)
        }
        mBinding.leftDrawerMenu.crossImgBtn.setOnClickListener {
            mBinding.drawerLayout.closeDrawer(Gravity.LEFT)
        }
    }


}