package com.cahyadesthian.onboarding.onboardingpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.onboarding.R
import com.cahyadesthian.onboarding.databinding.FragmentViewPagerBinding
import com.cahyadesthian.onboarding.onboardingpage.screens.FirstScreen
import com.cahyadesthian.onboarding.onboardingpage.screens.SecondScreen
import com.cahyadesthian.onboarding.onboardingpage.screens.ThirdScreen


class ViewPagerFragment : Fragment() {

    private var _viewPagerFragBinding : FragmentViewPagerBinding? = null
    private val viewPagerFragBinding get() = _viewPagerFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_view_pager, container, false)

        _viewPagerFragBinding = FragmentViewPagerBinding.inflate(inflater,container,false)
        val view = viewPagerFragBinding.root

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        viewPagerFragBinding.viewpager.adapter = adapter

        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _viewPagerFragBinding = null
    }

}