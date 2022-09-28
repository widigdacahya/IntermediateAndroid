package com.cahyadesthian.mytekslocalization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }



    private fun setupView() {



    }

    private fun setuplang() {

    }


}