package com.cahyadesthian.bottomnavthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.bottomnavthings.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {


    private var _thirdFragmentBInding: FragmentThirdBinding? = null
    private val thirdFragmentBinding get() = _thirdFragmentBInding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_third, container, false)

        _thirdFragmentBInding = FragmentThirdBinding.inflate(inflater,container,false)
        val view = thirdFragmentBinding.root

        return view


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _thirdFragmentBInding = null
    }

}