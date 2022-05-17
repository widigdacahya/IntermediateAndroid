package com.cahyadesthian.woofdog

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.cahyadesthian.woofdog.databinding.ActivityMainBinding
import com.cahyadesthian.woofdog.networking.ApiRequest
import com.cahyadesthian.woofdog.networking.BASE_URL
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        mainBinding.pbMainUI.visibility = View.VISIBLE

        backgroundAnimate()
        requestDogImgApi()

        mainBinding.fabRefreshMainUI.setOnClickListener {
            mainBinding.fabRefreshMainUI.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            requestDogImgApi()
            mainBinding.ivMainUI.visibility = View.GONE
        }


    }

    private fun backgroundAnimate() {
        val animationDrawable: AnimationDrawable = mainBinding.bgLayout.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun requestDogImgApi() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getRandomDog()
                Log.d("Main", "requestDogImgApi_Size: ${response.fileSizeBytes}")

                //if less than 4mb load it with glide
                if(response.fileSizeBytes<400_000) {
                    withContext(Dispatchers.Main) {
                        Glide.with(applicationContext).load(response.url).into(mainBinding.ivMainUI)
                        mainBinding.ivMainUI.visibility = View.VISIBLE
                        mainBinding.pbMainUI.visibility = View.GONE
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        mainBinding.pbMainUI.visibility = View.VISIBLE
                    }
                    requestDogImgApi()
                }
            } catch (e: Exception) {
                Log.e("Main", "Error : ${e.message} ")
            }
        }
    }



}