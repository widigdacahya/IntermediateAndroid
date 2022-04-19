package com.cahyadesthian.onboarding.onboardingpage.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.cahyadesthian.onboarding.R
import com.cahyadesthian.onboarding.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    private var _secondScreenFragBinding : FragmentSecondScreenBinding? = null
    private val secondScreenFragBinding get() = _secondScreenFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second_screen, container, false)

        _secondScreenFragBinding = FragmentSecondScreenBinding.inflate(inflater,container,false)
        val view = secondScreenFragBinding.root

        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewpager)

        secondScreenFragBinding.tvSecondNext.setOnClickListener {
            viewPager2?.currentItem = 2
        }

        return view


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _secondScreenFragBinding = null
    }

}