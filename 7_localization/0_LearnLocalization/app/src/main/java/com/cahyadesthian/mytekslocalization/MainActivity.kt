package com.cahyadesthian.mytekslocalization

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.MediaController
import com.cahyadesthian.mytekslocalization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        mainActivityBinding.videoview.setVideoPath("android.resource://"+ packageName +"/"+R.raw.wonderland)
        val mediaController =  MediaController(this)
        mediaController.setAnchorView(mainActivityBinding.videoview)
        mainActivityBinding.videoview.setMediaController(mediaController)
        supportActionBar?.hide()

        setupView()
    }



    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }


    }

    private fun setuplang() {

    }


}