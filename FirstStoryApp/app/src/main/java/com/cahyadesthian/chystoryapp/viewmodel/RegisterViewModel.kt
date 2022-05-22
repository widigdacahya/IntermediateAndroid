package com.cahyadesthian.chystoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.screen.util.Event
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

//    private val _loadingStateFlow = MutableStateFlow(true)
//    val loadingStateFlow = _loadingStateFlow.asStateFlow()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Event<Boolean>>()
    val isSuccess : LiveData<Event<Boolean>> = _isSuccess

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    fun registerUser(nameUser: String, emailUser: String, passwordUser: String) {
        //_loadingStateFlow.value = true
        _isLoading.value = true
        Api.retorofitApiService().registeringUser(nameUser,emailUser,passwordUser)
            .enqueue(object : Callback<InfoResponse> {
                override fun onResponse(
                    call: Call<InfoResponse>,
                    response: Response<InfoResponse>
                ) {
                    //_loadingStateFlow.value = false
                    _isLoading.value = false

                    if(response.isSuccessful) {
                        _isSuccess.value = Event(true)
                    } else {
                        val responseError = Gson().fromJson(
                            response.errorBody()!!.charStream(),
                            InfoResponse::class.java
                        )
                        _error.value = Event(responseError.message)
                    }
                }

                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                    //_loadingStateFlow.value = false
                    _isLoading.value = false

                    t.message?.let { Log.d("Failure", it)}
                    _error.value = Event(t.message.toString())
                }

            })
    }

}