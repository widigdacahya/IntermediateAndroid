package com.cahyadesthian.exovideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.exovideoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MainActivity : AppCompatActivity() {


    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val player = ExoPlayer.Builder(this).build()
        viewBinding.exoplateVidplaterMainUI.player = player

        val mediaItem = MediaItem.fromUri(URL_VIDEO_DICODING)
        //val mediaItem = MediaItem.fromUri(URL_VIDEO_YOUTUBE)

        val anotherMediaItem = MediaItem.fromUri(URL_AUDIO)

        player.setMediaItem(mediaItem)

        player.addMediaItem(anotherMediaItem)

        player.prepare()


    }



    companion object {
        const val URL_VIDEO_DICODING = "https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4"
        const val URL_VIDEO_YOUTUBE = "https://www.youtube.com/watch?v=iGKWjxgzSSM"

        const val URL_AUDIO = "https://github.com/dicodingacademy/assets/raw/main/android_intermediate_academy/bensound_ukulele.mp3"

    }
}