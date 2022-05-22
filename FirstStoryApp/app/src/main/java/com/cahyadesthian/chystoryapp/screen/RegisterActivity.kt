package com.cahyadesthian.chystoryapp.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cahyadesthian.chystoryapp.databinding.ActivityRegisterBinding
import com.cahyadesthian.chystoryapp.viewmodel.RegisterViewModel
import kotlinx.coroutines.flow.collectLatest

class RegisterActivity : AppCompatActivity() {

    private lateinit var regBinding : ActivityRegisterBinding

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(regBinding.root)


        loadingThings(false)
        registerViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(RegisterViewModel::class.java)

        supportActionBar?.title = "Register"

//        lifecycleScope.launchWhenCreated {
//            registerViewModel.loadingStateFlow.collectLatest {
//                if(it) {
//                    regBinding.loadingGroupRegister.visibility = View.VISIBLE
//                } else {
//                    regBinding.loadingGroupRegister.visibility = View.INVISIBLE
//                }
//            }
//        }

        registerViewModel.isSuccess.observe(this) {
            it.getIfNotHandled()?.let {
                if(it) {
                    Toast.makeText(this,"Success register", Toast.LENGTH_SHORT).show()
                }
            }
            val intentToLogin = Intent(this,LoginAccActivity::class.java)
            startActivity(intentToLogin)
        }

        registerViewModel.error.observe(this) {
            it.getIfNotHandled()?.let { errorMsg ->
                Toast.makeText(this,errorMsg, Toast.LENGTH_SHORT).show()
            }
        }

        registerViewModel.isLoading.observe(this) {
            loadingThings(it)
        }

        regBinding.btnRegister.setOnClickListener {
            registUserData()
        }

    }


    private fun registUserData() {
        val name = regBinding.customEdtNameRegister.text.toString()
        val email = regBinding.customEdtEmailRegister.text.toString()
        val password = regBinding.customEdtPassRegister.text.toString()
        registerViewModel.registerUser(name,email,password)
    }

    private fun loadingThings(isLoading: Boolean) {

        if(isLoading) {
            regBinding.loadingGroupRegister.visibility = View.VISIBLE
        } else {
            regBinding.loadingGroupRegister.visibility = View.INVISIBLE
        }

//        if(isLoading) {
////            lifecycleScope.launchWhenCreated {
////                registerViewModel.loadingStateFlow.collectLatest {
////                    if(it) {
////                        regBinding.loadingGroupRegister.visibility = View.VISIBLE
////                    } else {
////                        regBinding.loadingGroupRegister.visibility = View.INVISIBLE
////                    }
////                }
////            }
//            regBindin
//        } else {
//            regBinding.loadingGroupRegister.visibility = View.INVISIBLE
//        }



    }



}