package com.cahyadesthian.chystoryapp.screen

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentSplashBinding


class SplashFrag : Fragment() {

    private var _splashFragBinding : FragmentSplashBinding? = null
    private val splashFragBinding get() = _splashFragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _splashFragBinding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = splashFragBinding.root

        Handler().postDelayed({
            if (onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashFrag_to_authFragment)
            } else {
                findNavController().navigate(R.id.action_splashFrag_to_viewPagerOnBoarding)
            }
        },750)

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _splashFragBinding = null
    }

    //check shared pref(user done on boarding)
    private fun onBoardingFinished() : Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }

}