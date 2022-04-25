package com.cahyadesthian.thestateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.thestateflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _mainBinding: ActivityMainBinding? = null
    private val mainBinding get() = _mainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }


    override fun onDestroy() {

        super.onDestroy()
        _mainBinding = null

    }

}