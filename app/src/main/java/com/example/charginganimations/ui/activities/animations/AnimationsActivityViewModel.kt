package com.example.charginganimations.ui.activities.animations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.charginganimations.data.repositories.AnimationRepositories

class AnimationsActivityViewModel : ViewModel() {
    val animations: MutableList<Int> = ArrayList()

    fun getAnimationList(): MutableLiveData<List<Int>> {
        return AnimationRepositories.getAnimations()
    }
}