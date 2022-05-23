package com.cahyadesthian.chystoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.networking.ApiService
import com.cahyadesthian.chystoryapp.screen.util.Event
import com.cahyadesthian.chystoryapp.screen.util.reduceFileImage
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AddStoryViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<Event<String>>()
    val error: LiveData<Event<String>> = _error

    private val _isSucces = MutableLiveData<Event<Boolean>>()
    val isSuccess: LiveData<Event<Boolean>> = _isSucces


    fun addStory(image: File, desc: String, auth: String) {

        _isLoading.value = true

        val imageReduced = reduceFileImage(image)

        val descPart = desc.toRequestBody("text/plain".toMediaType())

        val imagePart = MultipartBody.Part.createFormData(
            ApiService.PHOTO,
            imageReduced.name,
            imageReduced.asRequestBody("image/jpeg".toMediaType())
        )

        Api.retorofitApiService().addStory(imagePart,descPart, auth)
            .enqueue(object : Callback<InfoResponse> {
                override fun onResponse(
                    call: Call<InfoResponse>,
                    response: Response<InfoResponse>
                ) {
                    _isLoading.value = false

                    if(response.isSuccessful) {
                        _isSucces.value = Event(true)
                    } else {
                        val errResp = Gson().fromJson(
                            response.errorBody()?.charStream(),
                            InfoResponse::class.java
                        )
                        _error.value = Event(errResp.message)
                    }

                }

                override fun onFailure(call: Call<InfoResponse>, t: Throwable) {
                    _isLoading.value = false

                    t.message?.let { Log.d("Failure", it)}
                    _error.value = Event(t.message.toString())
                }

            })


    }


}