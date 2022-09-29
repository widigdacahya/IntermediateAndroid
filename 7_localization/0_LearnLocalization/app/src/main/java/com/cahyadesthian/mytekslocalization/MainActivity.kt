package com.cahyadesthian.mytekslocalization

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
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

        /*
        mainActivityBinding.videoview.setVideoPath("android.resource://"+ packageName +"/"+R.raw.wonderland)
        val mediaController =  MediaController(this)
        mediaController.setAnchorView(mainActivityBinding.videoview)
        mainActivityBinding.videoview.setMediaController(mediaController)
        */

        supportActionBar?.hide()


        setupView()
        lanSetup()
        setupAccessibility()
    }



    private fun lanSetup() {
        mainActivityBinding.settingIcon.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
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

    private fun setupAccessibility() {
        mainActivityBinding.apply {
            settingIcon.contentDescription = getString(R.string.settingButtonDescription)
            gifview.contentDescription = getString(R.string.contentMainDescription)
            photoauthor.contentDescription = getString(R.string.contentAuthorPhoto,"Alffy Rev")
            tvNameAlfy.contentDescription = getString(R.string.authorName,"Alffy Rev")
        }
    }


}