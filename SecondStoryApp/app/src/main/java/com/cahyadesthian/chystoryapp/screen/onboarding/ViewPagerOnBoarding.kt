package com.cahyadesthian.chystoryapp.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentViewPagerOnBoardingBinding

class ViewPagerOnBoarding : Fragment() {

    private var _viewPagerFragBinding : FragmentViewPagerOnBoardingBinding? = null
    private val viewPagerOnBoardingBinding get() = _viewPagerFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentList = arrayListOf<Fragment>(
            FirstPage(),
            SecondPage(),
            ThirdPage()
        )

        _viewPagerFragBinding = FragmentViewPagerOnBoardingBinding.inflate(inflater,container,false)
        val view = viewPagerOnBoardingBinding.root


        val adapter = ViewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        viewPagerOnBoardingBinding.viewpageronboarding.adapter = adapter

        return view


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewPagerFragBinding = null
    }


}