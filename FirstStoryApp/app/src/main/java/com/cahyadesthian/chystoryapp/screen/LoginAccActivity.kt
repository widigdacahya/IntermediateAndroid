package com.cahyadesthian.chystoryapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.chystoryapp.databinding.ActivityLoginAccBinding
import com.cahyadesthian.chystoryapp.screen.customview.ValidateEditText


class LoginAccActivity : AppCompatActivity() {

    private lateinit var loginActBinding : ActivityLoginAccBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActBinding = ActivityLoginAccBinding.inflate(layoutInflater)
        setContentView(loginActBinding.root)

        loginActBinding.customEdtPasswordLogin.setOnValidityCallback(object : ValidateEditText.EditTextValidation {

            override fun validate(editTextInput: String): Boolean {
                return (editTextInput.length >= 6)
            }

            override val errorMessage: String
                get() = "At least need 6 character here"

        })

        supportActionBar?.title = "Login"



    }




}