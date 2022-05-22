package com.cahyadesthian.chystoryapp.screen

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentRegisterFixBinding
import com.cahyadesthian.chystoryapp.viewmodel.RegisterViewModel


class RegisterFragmentFix : Fragment() {

    private var _regFragBinding : FragmentRegisterFixBinding? = null
    private val regFragBinding get() = _regFragBinding!!

    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _regFragBinding = FragmentRegisterFixBinding.inflate(inflater,container,false)
        val view = regFragBinding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingThings(false)

        regFragBinding.btnRegisterFrag.setOnClickListener {
            registUserData()
        }

        registerViewModel.isLoading.observe(viewLifecycleOwner) {
            loadingThings(it)
        }

        registerViewModel.error.observe(viewLifecycleOwner) {
            it.getIfNotHandled()?.let { msg ->
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.isSuccess.observe(viewLifecycleOwner) {
            it.getIfNotHandled()?.let { registSuccess ->
                if(registSuccess) {
                    Toast.makeText(activity,"Resitration Success \uD83D\uDE46 ",Toast.LENGTH_SHORT).show()

//                    val fragmentLogin = LoginAccFragmentFix()
//                    val changeToLogin = activity?.supportFragmentManager?.beginTransaction()
//                    if (changeToLogin != null) {
//                        changeToLogin.replace(R.id.loginAccFragmentFix, fragmentLogin)
//                    }
//                    if (changeToLogin != null) {
//                        changeToLogin.commit()
//                    }
                    findNavController().navigate(R.id.action_registerFragmentFix_to_loginAccFragmentFix)

                }
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _regFragBinding = null
    }

    private fun loadingThings(isLoading: Boolean) {
        if(isLoading) {
            regFragBinding.loadingGroupRegisterFrag.visibility = View.VISIBLE
            regFragBinding.btnRegisterFrag.isEnabled = false
        } else {
            regFragBinding.loadingGroupRegisterFrag.visibility = View.GONE
            regFragBinding.btnRegisterFrag.isEnabled = true
        }

    }

    private fun registUserData() {
        val userName = regFragBinding.customEdtNameRegisterFrag.text.toString()
        val userEmail = regFragBinding.customEdtEmailRegisterFrag.text.toString()
        val userPassword = regFragBinding.customEdtPassRegisterFrag.text.toString()
        registerViewModel.registerUser(userName,userEmail,userPassword)
    }

}