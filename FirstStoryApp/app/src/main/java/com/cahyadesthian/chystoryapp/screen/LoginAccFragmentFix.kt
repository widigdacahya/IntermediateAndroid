package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentLoginAccFixBinding

class LoginAccFragmentFix : Fragment() {

    private var _loginFragBinding : FragmentLoginAccFixBinding? = null
    private val loginFragBinding get() = _loginFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _loginFragBinding = FragmentLoginAccFixBinding.inflate(inflater,container,false)
        val view = loginFragBinding.root

        return view
    }


}