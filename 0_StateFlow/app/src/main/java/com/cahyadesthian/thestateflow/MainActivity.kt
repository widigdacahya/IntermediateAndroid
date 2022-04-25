package com.cahyadesthian.thestateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.cahyadesthian.thestateflow.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var _mainBinding: ActivityMainBinding? = null
    private val mainBinding get() = _mainBinding!!

    private val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnLogin.setOnClickListener {
            viewmodel.login(
                mainBinding.edtName.text.toString(),mainBinding.edtPassword.text.toString()
            )
        }

        //observe event happen on flow
        lifecycleScope.launchWhenCreated {
            viewmodel.loginUiState.collect {
                when(it) {
                    is MainViewModel.LoginUiState.Success -> {
                        Snackbar.make(
                            mainBinding.root, "Login Success", Snackbar.LENGTH_LONG
                        ).show()
                        mainBinding.pbMain.isVisible = false
                    }

                    is MainViewModel.LoginUiState.Error -> {
                        Snackbar.make(
                            mainBinding.root, it.message, Snackbar.LENGTH_LONG
                        ).show()
                        mainBinding.pbMain.isVisible = false

                    }

                    is MainViewModel.LoginUiState.Loading -> {
                        mainBinding.pbMain.isVisible = true
                    }

                    else -> Unit


                }
            }
        }


    }


    override fun onDestroy() {

        super.onDestroy()
        _mainBinding = null

    }

}