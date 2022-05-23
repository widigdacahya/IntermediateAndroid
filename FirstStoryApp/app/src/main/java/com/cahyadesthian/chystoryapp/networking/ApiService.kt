package com.cahyadesthian.chystoryapp.networking

import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.model.LoginResponse
import com.cahyadesthian.chystoryapp.model.StoriesResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //https://story-api.dicoding.dev/v1

    @FormUrlEncoded
    @POST("register")
    fun registeringUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<InfoResponse>


    @FormUrlEncoded
    @POST("login")
    fun logginginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>


    @GET("stories")
    fun getAllStory(
        @Header("Authorization") token: String
    ): Call<StoriesResponse>


}