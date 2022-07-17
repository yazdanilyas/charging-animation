package com.example.charginganimations.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

object AppUtils {
    fun getVersionName(context: Context): String {
        val manager: PackageManager = context.packageManager
        var info: PackageInfo? = null
        try {
            info = manager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return "Version " + info?.versionName.toString()
    }

    fun setStatusBarColor(activity: AppCompatActivity, colorStatusBar: Int) {
        val window = activity.window
        window.statusBarColor = colorStatusBar//ContextCompat.getColor(activity, colorStatusBar)
    }

    fun setNavButtonBarColor(activity: AppCompatActivity, colorNavBar: Int) {
        val window = activity.window
        window.navigationBarColor = ContextCompat.getColor(activity, colorNavBar)
    }
}