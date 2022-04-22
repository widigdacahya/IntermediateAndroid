package com.cahyadesthian.chystoryapp.screen.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentThirdPageBinding

class ThirdPage : Fragment() {

    private var _thirdPageFragBinding : FragmentThirdPageBinding? = null
    private val thirdPageFragBinding get() = _thirdPageFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _thirdPageFragBinding = FragmentThirdPageBinding.inflate(inflater,container,false)
        val view = thirdPageFragBinding.root

        thirdPageFragBinding.tvFinishedNext.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerOnBoarding_to_authFragment)
            onBoardingFinished()
        }

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _thirdPageFragBinding = null
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }


}