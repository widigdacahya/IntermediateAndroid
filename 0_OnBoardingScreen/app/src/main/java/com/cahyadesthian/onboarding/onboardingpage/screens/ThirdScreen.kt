package com.cahyadesthian.onboarding.onboardingpage.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.onboarding.R
import com.cahyadesthian.onboarding.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {

    private var _thirdScreenFragBinding : FragmentThirdScreenBinding? = null
    private val thirdScreenFragBinding get() = _thirdScreenFragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_third_screen, container, false)

        _thirdScreenFragBinding = FragmentThirdScreenBinding.inflate(inflater,container,false)
        val view = thirdScreenFragBinding.root

        thirdScreenFragBinding.tvThirdFinish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
        }


        return view

    }




    override fun onDestroyView() {
        super.onDestroyView()
        _thirdScreenFragBinding = null
    }

}