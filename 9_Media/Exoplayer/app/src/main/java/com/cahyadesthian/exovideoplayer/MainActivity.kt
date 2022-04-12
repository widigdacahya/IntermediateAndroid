package com.cahyadesthian.exovideoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.cahyadesthian.exovideoplayer.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {

    private var player : ExoPlayer? = null


    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val player = ExoPlayer.Builder(this).build()
        viewBinding.exoplateVidplaterMainUI.player = player

        //moved to other fun to avoid bug when run in background
        //val mediaItem = MediaItem.fromUri(URL_VIDEO_DICODING)
        //val mediaItem = MediaItem.fromUri(URL_VIDEO_YOUTUBE)

        //val anotherMediaItem = MediaItem.fromUri(URL_AUDIO)

        //player.setMediaItem(mediaItem)

        //player.addMediaItem(anotherMediaItem)

        //player.prepare()


    }


    private fun initializePlayer() {
        val mediaItem = MediaItem.fromUri(URL_VIDEO_DICODING)
        val anotherMediaItem = MediaItem.fromUri(URL_AUDIO)

        player = ExoPlayer.Builder(this).build().also { exoPlayer ->
            viewBinding.exoplateVidplaterMainUI.player = exoPlayer
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.addMediaItem(anotherMediaItem)
            exoPlayer.prepare()
        }

    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }


    override fun onStart() {
        super.onStart()
        if(Util.SDK_INT >= 24) {
            initializePlayer()
        }


    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        if(Util.SDK_INT < 24 && player == null) {
            initializePlayer()
        }
    }


    override fun onPause() {
        super.onPause()
        if(Util.SDK_INT <= 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if(Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }


    /*
    * Full screen
    * */
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, viewBinding.exoplateVidplaterMainUI).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }



    companion object {
        const val URL_VIDEO_DICODING = "https://github.com/dicodingacademy/assets/releases/download/release-video/VideoDicoding.mp4"
        const val URL_VIDEO_YOUTUBE = "https://www.youtube.com/watch?v=iGKWjxgzSSM"

        const val URL_AUDIO = "https://github.com/dicodingacademy/assets/raw/main/android_intermediate_academy/bensound_ukulele.mp3"

    }
}