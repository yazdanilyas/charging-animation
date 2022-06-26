package com.example.charginganimations.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.charginganimations.R
import com.example.charginganimations.databinding.ItemAnimationBinding
import com.example.charginganimations.interfaces.RecyclerItemClickListener

class AnimationsAdapter(
    private val animationList: ArrayList<Int>,
    val recyclerItemClickListener: RecyclerItemClickListener
) :
    RecyclerView.Adapter<AnimationsAdapter.AnimationsViewHolder>() {


    class AnimationsViewHolder(val binding: ItemAnimationBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemAnimationBinding>(
            inflater,
            R.layout.item_animation,
            parent,
            false
        )
        return AnimationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimationsViewHolder, position: Int) {
        holder.binding.animDrawable = animationList[position]
        holder.binding.animationView.setOnClickListener {
            recyclerItemClickListener.onClick(animationList[position])
        }
        Log.d("TAGG", "onBindViewHolder: ${animationList[position]}")
    }

    override fun getItemCount(): Int {
        return animationList.size
    }
}