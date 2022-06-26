package com.example.charginganimations.utils

import com.example.charginganimations.R

object AnimationDataBuilder {


    fun birdsAnimationsBuilder(): ArrayList<Int> {
        val birdsAnimations = ArrayList<Int>()
        birdsAnimations.add(R.raw.birds_animation_1)
        birdsAnimations.add(R.raw.birds_animation_2)
        birdsAnimations.add(R.raw.birds_animation_3)
        birdsAnimations.add(R.raw.birds_animation_4)
        birdsAnimations.add(R.raw.birds_animation_5)
        birdsAnimations.add(R.raw.birds_animation_6)
        birdsAnimations.add(R.raw.birds_animation_7)
        birdsAnimations.add(R.raw.birds_animation_8)
        birdsAnimations.add(R.raw.birds_animation_9)
        return birdsAnimations;
    }

    fun heartAnimationsBuilder(): ArrayList<Int> {
        val heartAnimations = ArrayList<Int>()
        heartAnimations.add(R.raw.animation_heart_1)
        heartAnimations.add(R.raw.animation_heart_2)
        heartAnimations.add(R.raw.animation_heart_3)
        return heartAnimations;
    }

    fun circleAnimationsBuilder(): ArrayList<Int> {
        val circleAnimations = ArrayList<Int>()
        circleAnimations.add(R.raw.circle_animation_1)
        circleAnimations.add(R.raw.circle_animation_2)
        return circleAnimations;
    }

    fun flowersAnimationsBuilder(): ArrayList<Int> {
        val flowersAnimations = ArrayList<Int>()
        flowersAnimations.add(R.raw.flower_animation_1)
        return flowersAnimations;
    }
}