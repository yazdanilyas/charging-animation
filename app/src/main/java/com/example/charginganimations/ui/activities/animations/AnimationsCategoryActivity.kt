package com.example.charginganimations.ui.activities.animations

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.charginganimations.databinding.ActivityAnimationsCategoryBinding
import com.example.charginganimations.ui.activities.allanims.AllAnimationsActivity
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.Constants

class AnimationsCategoryActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAnimationsCategoryBinding
    private lateinit var viewModel: AnimationsActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimationsCategoryBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AnimationsActivityViewModel::class.java)
        setContentView(mBinding.root)
        setListeners()
    }

    private fun setListeners() {
        mBinding.birdsAnimationsBanner.setOnClickListener {
            openAllAnimationsActivity(Constants.ANIM_BIRDS)
        }
        mBinding.heartAnimationsBanner.setOnClickListener {
            openAllAnimationsActivity(Constants.ANIM_HEARTS)
        }
        mBinding.circleAnimationsBanner.setOnClickListener {
            openAllAnimationsActivity(Constants.ANIM_CIRCLES)
        }
        mBinding.flowersAnimationsBanner.setOnClickListener {
            openAllAnimationsActivity(Constants.ANIM_FLOWERS)
        }
    }

    private fun openAllAnimationsActivity(animType: String) {
        val intent = Intent(
            this@AnimationsCategoryActivity,
            AllAnimationsActivity::class.java
        )
        intent.putExtra(CommonKeys.KEY_ANIM_TYPE, animType)
        startActivity(intent)
    }


}