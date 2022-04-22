package com.example.charginganimations.ui.activities.animations

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.charginganimations.R
import com.example.charginganimations.adapters.AnimationsAdapter
import com.example.charginganimations.databinding.ActivityAnimationsBinding
import com.example.charginganimations.interfaces.RecyclerItemClickListener
import com.example.charginganimations.services.ChargingService
import com.example.charginganimations.ui.activities.animationpreview.AnimationPreviewActivity
import com.example.charginganimations.utils.CommonKeys

class AnimationsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAnimationsBinding
    private lateinit var viewModel: AnimationsActivityViewModel
    private lateinit var mAdapter: AnimationsAdapter
    private val animationList = ArrayList<Int>()
    private val recyclerItemClickListener = object : RecyclerItemClickListener {
        override fun onClick(animationImg: Int) {
            val intent = Intent(applicationContext, AnimationPreviewActivity::class.java)
            intent.putExtra(CommonKeys.KEY_ANIM_GIF, animationImg)
            startActivity(intent)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(Intent(this@AnimationsActivity, ChargingService::class.java))
            } else {
                startService(Intent(this@AnimationsActivity, ChargingService::class.java))
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAnimationsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(AnimationsActivityViewModel::class.java)
        setContentView(mBinding.root)
        getAnimations()
        setRecyclerAdapter()
    }

    private fun getAnimations() {
        animationList.add(R.drawable.anim1)
        animationList.add(R.drawable.anim2)
        animationList.add(R.drawable.anim3)
//        viewModel.getAnimationList().observe(this) {
//            if (it != null && it.isNotEmpty()) {
//                viewModel.animations.clear()
//                viewModel.animations.addAll(it)
//
//            }
//            if (::mAdapter.isInitialized) {
//                mAdapter.notifyDataSetChanged()
//            }
//
//
//        }
    }

    private fun setRecyclerAdapter() {
        mAdapter = AnimationsAdapter(animationList, recyclerItemClickListener)
//        mAdapter = AnimationPreviewAdapter(viewModel.animations as ArrayList<Int>)
        mBinding.animationRecycler.layoutManager = GridLayoutManager(this, 2)
        mBinding.animationRecycler.adapter = mAdapter

    }
}