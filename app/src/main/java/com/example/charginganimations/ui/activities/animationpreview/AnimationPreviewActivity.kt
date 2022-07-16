package com.example.charginganimations.ui.activities.animationpreview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.databinding.ActivityAnimationPreviewBinding
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.DialogUtils
import com.example.charginganimations.utils.PrefUtils

class AnimationPreviewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAnimationPreviewBinding
    private var animGif: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimationPreviewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        previewAnimation()
        setListeners()
    }

    private fun setListeners() {
        mBinding.applyAnimButton.setOnClickListener {
//            DialogUtils.withItems(this@AnimationPreviewActivity, null)
            DialogUtils.bottomSheetDialog(this@AnimationPreviewActivity)
            PrefUtils.setInt(this@AnimationPreviewActivity, CommonKeys.KEY_ANIM_GIF, animGif)
            Toast.makeText(this, "Animation applied", Toast.LENGTH_LONG).show()
        }
    }

    private fun previewAnimation() {
        animGif = intent.extras?.getInt(CommonKeys.KEY_ANIM_GIF)!!
        mBinding.animationView.setAnimation(animGif)
//        Glide.with(this).asGif().load(animGif).into(mBinding.animationImg)
    }
}