package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentLoginAccFixBinding
import com.cahyadesthian.chystoryapp.screen.util.SessionDataPreference
import com.cahyadesthian.chystoryapp.viewmodel.LoginViewModel
import com.cahyadesthian.chystoryapp.viewmodel.SharedViewModel

class LoginAccFragmentFix : Fragment() {

    private var _loginFragBinding : FragmentLoginAccFixBinding? = null
    private val loginFragBinding get() = _loginFragBinding!!

    private val loginViewModel by viewModels<LoginViewModel>()
    private val sharedViewModel by activityViewModels<SharedViewModel>{
        SharedViewModel.Factory(SessionDataPreference.getDataStoreInstance(context?.dataStore as DataStore))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _loginFragBinding = FragmentLoginAccFixBinding.inflate(inflater,container,false)
        val view = loginFragBinding.root

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingThings(false)

        loginFragBinding.btnLoginFrag.setOnClickListener {
            loginWithUserData()
        }

        loginViewModel.isLoading.observe(viewLifecycleOwner) {
            loadingThings(it)
        }

        loginViewModel.error.observe(viewLifecycleOwner) {
            it.getIfNotHandled()?.let { msg ->
                Toast.makeText(activity,msg, Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.userToken.observe(viewLifecycleOwner) {
            it.getIfNotHandled()?.let {
                sharedViewModel.storeToken(it)
                Toast.makeText(activity,"Logging you in \uD83D\uDC4C", Toast.LENGTH_SHORT).show()
            }
        }

        sharedViewModel.seeToken().observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                goToStoryList(it)
            }
        }

    }


    private fun loadingThings(isLoading: Boolean) {
        if(isLoading) {
            loginFragBinding.loadingGroupLoginFrag.visibility = View.VISIBLE
            loginFragBinding.btnLoginFrag.isEnabled = false
        }else {
            loginFragBinding.loadingGroupLoginFrag.visibility = View.GONE
            loginFragBinding.btnLoginFrag.isEnabled = true
        }
    }

    private fun loginWithUserData() {
        val userEmail = loginFragBinding.customEmailLoginFixFrag.text.toString()
        val userPass = loginFragBinding.customPassLoginFixFrag.text.toString()
        loginViewModel.loginUser(userEmail,userPass)
    }

    private fun goToStoryList(userToken: String) {

    }


    override fun onDestroy() {
        super.onDestroy()
        _loginFragBinding = null
    }

}