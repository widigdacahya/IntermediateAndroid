package com.cahyadesthian.chystoryapp.screen.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentFirstPageBinding


class FirstPage : Fragment() {

    private var _firstPageFragBinding : FragmentFirstPageBinding? = null
    private val firstPageFragBinding get() = _firstPageFragBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _firstPageFragBinding = FragmentFirstPageBinding.inflate(inflater,container,false)
        val view = firstPageFragBinding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpageronboarding)

        firstPageFragBinding.tvFirstNext.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _firstPageFragBinding = null
    }

}