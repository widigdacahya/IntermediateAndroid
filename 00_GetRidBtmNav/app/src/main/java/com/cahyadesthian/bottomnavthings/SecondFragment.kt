package com.cahyadesthian.bottomnavthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.bottomnavthings.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _secondFragmentBinding : FragmentSecondBinding? = null
    private val secondFragmentBinding get() = _secondFragmentBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_second, container, false)

        _secondFragmentBinding = FragmentSecondBinding.inflate(inflater,container,false)
        val view = secondFragmentBinding.root

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _secondFragmentBinding = null
    }
}