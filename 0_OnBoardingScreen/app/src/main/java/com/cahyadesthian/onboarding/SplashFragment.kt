package com.cahyadesthian.onboarding

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.onboarding.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _splashFragBinding: FragmentSplashBinding? = null
    private val splasFragBinding get() = _splashFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_splash, container, false)

        _splashFragBinding = FragmentSplashBinding.inflate(inflater,container,false)
        val view = splasFragBinding.root


        Handler().postDelayed({
            if(onBoardingFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }


        },1000)




        return view

    }

    //check shared pref(user done on boarding)
    private fun onBoardingFinished() : Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished",false)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _splashFragBinding = null
    }




}