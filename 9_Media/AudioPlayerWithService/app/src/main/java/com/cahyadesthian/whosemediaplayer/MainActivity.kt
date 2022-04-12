package com.cahyadesthian.whosemediaplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.cahyadesthian.whosemediaplayer.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private var mService : Messenger? = null
    private lateinit var mBoundServiceIntent: Intent
    private var mServiceBound = false

    private val mServiceConnection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            mServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mService = null
            mServiceBound = false
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        mBoundServiceIntent = Intent(this@MainActivity, MediaService::class.java)
        mBoundServiceIntent.action = MediaService.ACTION_CREATE

        startService(mBoundServiceIntent)
        bindService(mBoundServiceIntent,mServiceConnection, Context.BIND_AUTO_CREATE)

        mainBinding.btnPlayMainUI.setOnClickListener {


                if(mServiceBound) {
                    try {
                        mService?.send(Message.obtain(null, MediaService.PLAY,0,0))
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }



        }

        mainBinding.btnStopMainUI.setOnClickListener {
            if(mServiceBound) {
                    try {
                        mService?.send(Message.obtain(null,MediaService.STOP,0,0))
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        unbindService(mServiceConnection)
        mBoundServiceIntent.action = MediaService.ACTION_DESTROY

        startService(mBoundServiceIntent)
    }

    companion object {
        const val TAG = "MainActivity"
    }

//    override fun onClick(v: View) {
//        when(v.id) {
//
//            R.id.btn_play_mainUI -> {
//                if(mServiceBound) {
//                    try {
//                        mService?.send(Message.obtain(null, MediaService.PLAY,0,0))
//                    } catch (e: RemoteException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//            R.id.btn_stop_mainUI -> {
//                if(mServiceBound) {
//                    try {
//                        mService?.send(Message.obtain(null,MediaService.STOP,0,0))
//                    } catch (e: RemoteException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//
//
//        }
//    }


}