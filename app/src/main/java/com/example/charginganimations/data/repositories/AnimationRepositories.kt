package com.example.charginganimations.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.charginganimations.R

class AnimationRepositories {
    companion object {
        private lateinit var animations: MutableLiveData<List<Int>>

        fun getAnimations(): MutableLiveData<List<Int>> {
            val anims = ArrayList<Int>()
            anims.add(R.drawable.anim1)
            anims.add(R.drawable.anim2)
            anims.add(R.drawable.anim3)
            animations.value = anims
            return animations
        }


    }

}