package com.example.charginganimations.data.repositories

import androidx.lifecycle.MutableLiveData

class AnimationRepositories {
    companion object {
        private lateinit var animations: MutableLiveData<List<Int>>

        fun getAnimations(): MutableLiveData<List<Int>> {
            val anims = ArrayList<Int>()

            animations.value = anims
            return animations
        }


    }

}