package com.example.charginganimations.ui.activities.allanims

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.charginganimations.adapters.AnimationsAdapter
import com.example.charginganimations.databinding.ActivityAllAnimationsBinding
import com.example.charginganimations.interfaces.RecyclerItemClickListener
import com.example.charginganimations.services.ChargingService
import com.example.charginganimations.ui.activities.animationpreview.AnimationPreviewActivity
import com.example.charginganimations.utils.AnimationDataBuilder
import com.example.charginganimations.utils.CommonKeys
import com.example.charginganimations.utils.Constants

class AllAnimationsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAllAnimationsBinding
    private lateinit var mAdapter: AnimationsAdapter
    private var animsDataSet = ArrayList<Int>()
    private var animType: String? = null
    private val recyclerItemClickListener = object : RecyclerItemClickListener {
        override fun onClick(animationImg: Int) {
            val intent = Intent(applicationContext, AnimationPreviewActivity::class.java)
            intent.putExtra(CommonKeys.KEY_ANIM_GIF, animationImg)
            startActivity(intent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(
                    Intent(
                        this@AllAnimationsActivity,
                        ChargingService::class.java
                    )
                )
            } else {
                startService(Intent(this@AllAnimationsActivity, ChargingService::class.java))
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAllAnimationsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getIntentData()
        prepareAnimationData()
        setRecyclerAdapter()
    }

    private fun getIntentData() {
        animType = intent.extras?.getString(CommonKeys.KEY_ANIM_TYPE)
    }

    private fun prepareAnimationData() {
        if (animType == Constants.ANIM_BIRDS) {
            animsDataSet = AnimationDataBuilder.birdsAnimationsBuilder()
        } else if (animType == Constants.ANIM_HEARTS) {
            animsDataSet = AnimationDataBuilder.heartAnimationsBuilder()
        } else if (animType == Constants.ANIM_CIRCLES) {
            animsDataSet = AnimationDataBuilder.circleAnimationsBuilder()
        } else if (animType == Constants.ANIM_FLOWERS) {
            animsDataSet = AnimationDataBuilder.flowersAnimationsBuilder()
        }

    }

    private fun setRecyclerAdapter() {
        mAdapter = AnimationsAdapter(animsDataSet, recyclerItemClickListener)
//        mAdapter = AnimationPreviewAdapter(viewModel.animations as ArrayList<Int>)
        mBinding.animationsRecycler.layoutManager = GridLayoutManager(this, 2)
        mBinding.animationsRecycler.adapter = mAdapter

    }
}