package com.cahyadesthian.chystoryapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.chystoryapp.databinding.ActivityLoginAccBinding


class LoginAccActivity : AppCompatActivity() {

    private lateinit var loginActBinding : ActivityLoginAccBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActBinding = ActivityLoginAccBinding.inflate(layoutInflater)
        setContentView(loginActBinding.root)

    }




}