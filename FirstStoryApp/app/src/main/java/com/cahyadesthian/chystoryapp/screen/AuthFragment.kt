package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private var _authFragBinding : FragmentAuthBinding? = null
    private val authFragBinding get() = _authFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _authFragBinding = FragmentAuthBinding.inflate(inflater,container,false)
        val view = authFragBinding.root

        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _authFragBinding = null
    }

}