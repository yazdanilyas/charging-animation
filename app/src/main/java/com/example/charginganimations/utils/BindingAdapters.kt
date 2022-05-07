package com.example.charginganimations.utils

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.charginganimations.R
import com.example.charginganimations.application.AnimationApp


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setAnimation")
    fun setAnimation(imageView: AppCompatImageView, drawable: Int?) {
        Log.d("TAGG", "setAnimation: ")
        Glide.with(AnimationApp.getAppContext()).asGif().placeholder(R.drawable.anim1)
            .load(drawable).into(imageView)
    }
}