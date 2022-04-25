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

        supportActionBar?.title = "Login"



    }




}