package com.cahyadesthian.customsafeargs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cahyadesthian.customsafeargs.R
import com.cahyadesthian.customsafeargs.databinding.FragmentDataReceiverBinding


class DataReceiverFragment : Fragment() {

    private var _receiverFragBinding: FragmentDataReceiverBinding? = null
    private val receiverFragBinding get() = _receiverFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _receiverFragBinding = FragmentDataReceiverBinding.inflate(inflater,container,false)
        val view = receiverFragBinding.root

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _receiverFragBinding = null
    }


}