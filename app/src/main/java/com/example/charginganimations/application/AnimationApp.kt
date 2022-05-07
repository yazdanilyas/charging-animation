package com.example.charginganimations.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class AnimationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        fun getAppContext(): Context {
            return context
        }
    }
}