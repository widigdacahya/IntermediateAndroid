package com.cahyadesthian.chystoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.model.LoginResponse
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.networking.ApiService
import com.cahyadesthian.chystoryapp.screen.util.Event
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _userToken = MutableLiveData<Event<String>>()
    val userToken: LiveData<Event<String>> = _userToken

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error= MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    fun loginUser(userEmail:String, userPassword: String) {
        _isLoading.value = true
        Api.retorofitApiService().logginginUser(userEmail,userPassword)
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    _isLoading.value = false

                    if(response.isSuccessful) {
                        val userToken = response.body()?.loginResult?.token?: "token"
                        _userToken.value = Event(userToken)
                    } else {
                        val responseError = Gson().fromJson(
                            response.errorBody()!!.charStream(),
                            InfoResponse::class.java
                        )
                        _error.value = Event(responseError.message)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                    _isLoading.value = false

                    t.message?.let { Log.d("Failure login", it)}

                    _error.value = Event(t.message.toString())
                }

            })
    }



}