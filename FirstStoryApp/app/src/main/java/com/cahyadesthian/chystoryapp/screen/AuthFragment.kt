package com.cahyadesthian.chystoryapp.screen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.fragment.app.Fragment
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

        animateAuth()

        return view

    }

    private fun goToStories(userToken: String) {
        //findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
        val toLoginToBeLogged = AuthFragmentDirections.actionAuthFragmentToLoginFragment()
        findNavController().navigate(toLoginToBeLogged)
    }

    private fun animateAuth() {

        val illustrateAnimate = ObjectAnimator.ofFloat(authFragBinding.cvImage, View.ALPHA,1f).setDuration(1300)

        val tvWelcomeAnimate = ObjectAnimator.ofFloat(authFragBinding.tvWelcome,View.ALPHA,1f).setDuration(500)
        val tvDescAnimate = ObjectAnimator.ofFloat(authFragBinding.tvDesc, View.ALPHA,1f).setDuration(500)
        val btnRegisterAnimate = ObjectAnimator.ofFloat(authFragBinding.btnRegister, View.ALPHA,1f).setDuration(500)
        val btnSignInAnimate = ObjectAnimator.ofFloat(authFragBinding.btnSignin, View.ALPHA,1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(illustrateAnimate,tvWelcomeAnimate,tvDescAnimate,btnRegisterAnimate,btnSignInAnimate)
            start()
        }

        ObjectAnimator.ofFloat(authFragBinding.cvImage,View.TRANSLATION_X,-25f,25f).apply {
            duration=6000
            repeatMode = ObjectAnimator.REVERSE
            repeatCount = ObjectAnimator.INFINITE
        }.start()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _authFragBinding = null
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }



}