package com.cahyadesthian.chystoryapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.FragmentAuthBinding
import com.cahyadesthian.chystoryapp.screen.util.SessionDataPreference
import com.cahyadesthian.chystoryapp.viewmodel.SharedViewModel


class AuthFragment : Fragment() {

    private var _authFragBinding : FragmentAuthBinding? = null
    private val authFragBinding get() = _authFragBinding!!

    private val sharedViewModel by activityViewModels<SharedViewModel> {
        SharedViewModel.Factory(SessionDataPreference.getDataStoreInstance(context?.dataStore as DataStore))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sharedViewModel.seeToken().observe(viewLifecycleOwner) {
            if(it.isNotEmpty()) {
                goToStories(it)
            }
        }

        _authFragBinding = FragmentAuthBinding.inflate(inflater,container,false)
        val view = authFragBinding.root

        authFragBinding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragmentFix)
        }

        authFragBinding.btnSignin.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_loginFragment)
        }

        return view

    }

    private fun goToStories(userToken: String) {
        //findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
        val toLoginToBeLogged = AuthFragmentDirections.actionAuthFragmentToLoginFragment()
        findNavController().navigate(toLoginToBeLogged)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _authFragBinding = null
    }

}