package com.cahyadesthian.bottomnavthings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.bottomnavthings.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _firstFragmentBinding : FragmentFirstBinding? = null
    private val firstFragmentBinding get() = _firstFragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)

        _firstFragmentBinding = FragmentFirstBinding.inflate(inflater,container,false)
        val view = firstFragmentBinding.root

        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _firstFragmentBinding = null
    }

}