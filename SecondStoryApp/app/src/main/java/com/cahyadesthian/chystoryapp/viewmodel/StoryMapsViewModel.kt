package com.cahyadesthian.chystoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.model.StoriesResponse
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.screen.util.Event
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoryMapsViewModel(private val authThings: String): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> =_isLoading

    private val _error = MutableLiveData<Event<String>>()
    val error : LiveData<Event<String>> = _error

    private val _allStory = MutableLiveData<List<ItemListStory>>()
    val allStory : LiveData<List<ItemListStory>> = _allStory

    init {
        getAllStoryData()
    }

    fun getAllStoryData() {

        _isLoading.value = true

        Api.retorofitApiService().getAllStory(authThings)
            .enqueue(object : Callback<StoriesResponse> {
                override fun onResponse(
                    call: Call<StoriesResponse>,
                    response: Response<StoriesResponse>
                ) {

                    _isLoading.value = false

                    if(response.isSuccessful) {

                        _allStory.value = response.body()?.listStory

                    } else {

                        val responseError = Gson().fromJson(
                            response.errorBody()!!.charStream(),
                            InfoResponse::class.java
                        )
                        _error.value = Event(responseError.message)

                    }

                }

                override fun onFailure(call: Call<StoriesResponse>, t: Throwable) {
                    _isLoading.value = false

                    t.message?.let { Log.d("Failure story maps", it)}
                    _error.value = Event(t.message.toString())
                }

            })

    }

    class Factory(private val authThings: String) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StoryMapsViewModel(authThings) as T
        }

    }

}