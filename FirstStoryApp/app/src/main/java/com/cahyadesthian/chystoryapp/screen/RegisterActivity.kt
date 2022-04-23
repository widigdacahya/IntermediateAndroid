package com.cahyadesthian.chystoryapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.chystoryapp.R
import com.cahyadesthian.chystoryapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var regBinding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        regBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(regBinding.root)



    }
}