package com.cahyadesthian.chystoryapp.networking

import com.cahyadesthian.chystoryapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Api {

    companion object {
        fun retorofitApiService() : ApiService {
            val interceptor = if(BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

            val client = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
            }.build()

            val retrofitApi= Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiInstance: ApiService by lazy {
                retrofitApi.create(ApiService::class.java)
            }

            return apiInstance
        }
    }
}