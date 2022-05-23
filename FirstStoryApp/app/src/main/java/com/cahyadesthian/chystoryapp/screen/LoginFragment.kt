package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.databinding.FragmentLoginBinding
import com.cahyadesthian.chystoryapp.screen.util.SessionDataPreference
import com.cahyadesthian.chystoryapp.viewmodel.LoginViewModel
import com.cahyadesthian.chystoryapp.viewmodel.SharedViewModel

class LoginFragment : Fragment() {

    private var _loginBinding : FragmentLoginBinding? = null
    private val loginBinding get() = _loginBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    private val sharedViewModel by activityViewModels<SharedViewModel> {
        SharedViewModel.Factory(SessionDataPreference.getDataStoreInstance(context?.dataStore as DataStore))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _loginBinding = FragmentLoginBinding.inflate(inflater,container,false)
        return loginBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingThings(false)

        loginBinding?.btnLoginFrag?.setOnClickListener {
            loginTheUser()
        }

        loginViewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                loadingThings(it)
            }

            userToken.observe(viewLifecycleOwner) {
                it.getIfNotHandled()?.let {
                    loggedUserIn(it)
                }
            }

            error.observe(viewLifecycleOwner) {
                it.getIfNotHandled()?.let { message ->
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        sharedViewModel.seeToken().observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                goToStories(it)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _loginBinding = null
    }

    private fun loadingThings(isLoading: Boolean) {
        if(isLoading) {
            loginBinding?.loadingGroupLoginFrag?.visibility = View.VISIBLE
            loginBinding?.btnLoginFrag?.isEnabled = false
        } else {
            loginBinding?.loadingGroupLoginFrag?.visibility = View.GONE
            loginBinding?.btnLoginFrag?.isEnabled = true
        }

    }

    private fun loginTheUser() {
        with(loginBinding) {
            val userEmail = this?.customEmailLoginFixFrag?.text.toString()
            val userPassword = this?.customPassLoginFixFrag?.text.toString()
            loginViewModel.loginUser(userEmail, userPassword )
        }

    }

    private fun loggedUserIn(userToken: String) {
        sharedViewModel.storeToken(userToken)
    }

    private fun goToStories(token:String) {
        val navToStoryList = LoginFragmentDirections.actionLoginFragmentToStoriesFragment(token)
        findNavController().navigate(navToStoryList)
    }


}