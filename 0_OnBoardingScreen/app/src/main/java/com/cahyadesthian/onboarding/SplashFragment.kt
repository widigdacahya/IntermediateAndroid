package com.cahyadesthian.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _splashFragBinding = null
    }


}