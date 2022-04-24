package com.cahyadesthian.chystoryapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
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
                get() = "Whoops, at least need 6 character here"

        })

        loginActBinding.customEdtEmailLogin.setOnValidityCallback(object : ValidateEditText.EditTextValidation {

            override fun validate(editTextInput: String): Boolean {
                return Patterns.EMAIL_ADDRESS.matcher(editTextInput).matches()
            }

            override val errorMessage: String
                get() = "Whoops, please type the correct email"


        })



        supportActionBar?.title = "Login"



    }




}