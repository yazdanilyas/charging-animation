package com.example.charginganimations.ui.activities.animationpreview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.charginganimations.R
import com.example.charginganimations.databinding.ActivityAnimationPreviewBinding
import com.example.charginganimations.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialog

class AnimationPreviewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAnimationPreviewBinding
    private var animGif: Int = 0
    val bottomSheetListener = object : DialogUtils.BottomSheetListener {
        override fun onLockScreenClick(bottomSheetDialog: BottomSheetDialog) {
            PrefUtils.setInt(
                this@AnimationPreviewActivity,
                CommonKeys.KEY_APPLY_ON,
                Constants.APPLY_LOCK_SCREEN
            )
            applyAnimation(getString(R.string.anim_apply_lock_screen))
            bottomSheetDialog.dismiss()
        }

        override fun onChargingScreenClick(bottomSheetDialog: BottomSheetDialog) {
            PrefUtils.setInt(
                this@AnimationPreviewActivity,
                CommonKeys.KEY_APPLY_ON,
                Constants.APPLY_CHARGING_SCREEN
            )
            applyAnimation(getString(R.string.anim_applied_charging_screen))
            bottomSheetDialog.dismiss()
        }

        override fun onBothClick(bottomSheetDialog: BottomSheetDialog) {
            PrefUtils.setInt(
                this@AnimationPreviewActivity,
                CommonKeys.KEY_APPLY_ON,
                Constants.APPLY_BOTH_SCREEN
            )
            applyAnimation(getString(R.string.anim_applied_both_screen))
            bottomSheetDialog.dismiss()
        }

        override fun onCancel(bottomSheetDialog: BottomSheetDialog) {
            bottomSheetDialog.dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimationPreviewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        AppUtils.setStatusBarColor(this, R.color.blue)
        previewAnimation()
        setListeners()
    }

    private fun setListeners() {
        mBinding.applyAnimButton.setOnClickListener {
//            DialogUtils.withItems(this@AnimationPreviewActivity, null)
            DialogUtils.bottomSheetDialog(this@AnimationPreviewActivity, bottomSheetListener)

        }
    }

    private fun previewAnimation() {
        animGif = intent.extras?.getInt(CommonKeys.KEY_ANIM_GIF)!!
        mBinding.animationView.setAnimation(animGif)
//        Glide.with(this).asGif().load(animGif).into(mBinding.animationImg)
    }

    private fun applyAnimation(message: String) {
        PrefUtils.setInt(this@AnimationPreviewActivity, CommonKeys.KEY_ANIM_GIF, animGif)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}