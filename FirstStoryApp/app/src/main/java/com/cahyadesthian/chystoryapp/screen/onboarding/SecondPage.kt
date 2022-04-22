package com.cahyadesthian.chystoryapp.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentSecondPageBinding


class SecondPage : Fragment() {

    private var _secondPageFragBinding : FragmentSecondPageBinding? = null
    private val secondPageFragBinding get() = _secondPageFragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _secondPageFragBinding = FragmentSecondPageBinding.inflate(inflater,container,false)
        val view = secondPageFragBinding.root

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _secondPageFragBinding = null
    }


}