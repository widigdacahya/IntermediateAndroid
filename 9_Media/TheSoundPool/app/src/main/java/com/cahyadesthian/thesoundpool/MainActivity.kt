package com.cahyadesthian.thesoundpool

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cahyadesthian.thesoundpool.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    private lateinit var sp : SoundPool
    private var soundId : Int = 0
    private var spLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()


        sp.setOnLoadCompleteListener { _, _, status ->

            if(status == 0) spLoaded = true else Toast.makeText(this@MainActivity, "Load Failed", Toast.LENGTH_SHORT).show()

        }

        soundId = sp.load(this, R.raw.bird_gaje, 1)


        mainBinding.btnPlaySoundpoolMainUI.setOnClickListener {
            if(spLoaded) sp.play(soundId,1f,1f,0,5,1f)
        }

        /**
         * Parameter play
         * int play (
                int soundID,
                float leftVolume,
                float rightVolume,
                int priority,
                int loop,
                float rate
                )
         * */


    }
}