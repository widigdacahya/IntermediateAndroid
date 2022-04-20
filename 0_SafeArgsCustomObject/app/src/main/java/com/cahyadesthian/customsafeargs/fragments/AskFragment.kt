package com.cahyadesthian.customsafeargs.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.customsafeargs.databinding.FragmentAskBinding
import com.cahyadesthian.customsafeargs.model.User


class AskFragment : Fragment() {

    private var _askFragBinding: FragmentAskBinding? = null
    private val askFragBinding get() = _askFragBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _askFragBinding = FragmentAskBinding.inflate(inflater,container,false)
        val view = askFragBinding.root


        askFragBinding.btnSendAskfrag.setOnClickListener {
            val userInputFirstName = askFragBinding.edtFirstnameAskfrag.text.toString()
            val userInputLastName = askFragBinding.edtLastnameAskfrag.text.toString()

            val user = User(userInputFirstName,userInputLastName)

            val action = AskFragmentDirections.actionAskFragmentToDataReceiverFragment(user)
            findNavController().navigate(action)

        }


        return view

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _askFragBinding = null
    }

}