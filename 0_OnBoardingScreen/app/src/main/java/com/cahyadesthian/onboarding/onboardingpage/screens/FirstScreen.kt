package com.cahyadesthian.onboarding.onboardingpage.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.onboarding.R
import com.cahyadesthian.onboarding.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    private var _firstScreenFragBinding : FragmentFirstScreenBinding? = null
    private val firstScreenFragBinding get() = _firstScreenFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first_screen, container, false)

        _firstScreenFragBinding = FragmentFirstScreenBinding.inflate(inflater,container,false)
        val view = firstScreenFragBinding.root

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _firstScreenFragBinding = null
    }

}