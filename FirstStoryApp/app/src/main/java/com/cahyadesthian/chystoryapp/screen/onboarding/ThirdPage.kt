package com.cahyadesthian.chystoryapp.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _thirdPageFragBinding = null
    }


}