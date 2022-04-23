package com.example.charginganimations.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.charginganimations.R
import com.example.charginganimations.databinding.FragmentDashboardBinding
import com.example.charginganimations.ui.activities.animations.AnimationsActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DashboardFragment : Fragment() {
    private lateinit var mBinding: FragmentDashboardBinding

    private var param1: String? = null
    private var param2: String? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openDrawerView()
        setListeners()
    }

    private fun setListeners() {
        mBinding.animationsCv.setOnClickListener {
            startActivity(Intent(activity, AnimationsActivity::class.java))
        }
    }

    private fun openDrawerView() {
        val profileImage = mBinding.root.findViewById<AppCompatImageView>(R.id.openDrawerImg)
        profileImage?.setOnClickListener {
            val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawerLayout)
            drawer.openDrawer(Gravity.LEFT)
        }
    }


}