package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _splashFragBinding = null
    }


}