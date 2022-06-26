package com.example.charginganimations.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView


object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setAnimation")
    fun setAnimation(animView: LottieAnimationView, drawable: Int?) {
        Log.d("TAGG", "setAnimation: ")
        if (drawable != null) {
            animView.setAnimation(drawable)
        }
//        Glide.with(AnimationApp.getAppContext()).asGif().placeholder(R.drawable.anim1)
//            .load(drawable).into(imageView)
    }
}