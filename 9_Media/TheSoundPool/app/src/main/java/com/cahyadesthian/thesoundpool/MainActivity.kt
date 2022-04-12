package com.cahyadesthian.thesoundpool

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var sp : SoundPool
    private var soundId : Int = 0
    private var spLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()


        sp.setOnLoadCompleteListener { _, _, status ->

            if(status == 0) spLoaded = true else Toast.makeText(this@MainActivity, "Load Failed", Toast.LENGTH_SHORT).show()

        }

        soundId = sp.load(this, R.raw.bird_gaje, 1)

    }
}