package com.cahyadesthian.chystoryapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.ActivityRegisterBinding

import com.cahyadesthian.chystoryapp.screen.customview.ValidateEditText

class RegisterActivity : AppCompatActivity() {

    private lateinit var regBinding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(regBinding.root)

        regBinding.customEdtPassRegister.setOnValidityCallback(object: ValidateEditText.EditTextValidation {

            override fun validate(editTextInput: String): Boolean {
                return (editTextInput.length >= 6)
            }

            override val errorMessage: String
                get() = "Whoops,at least need 6 character here"
        })


        regBinding.customEdtEmailRegister.setOnValidityCallback(object: ValidateEditText.EditTextValidation{

            override fun validate(editTextInput: String): Boolean {
                return Patterns.EMAIL_ADDRESS.matcher(editTextInput).matches()
            }

            override val errorMessage: String
                get() = "Whoops, please type the correct email"

        })


        supportActionBar?.title = "Register"

    }
}