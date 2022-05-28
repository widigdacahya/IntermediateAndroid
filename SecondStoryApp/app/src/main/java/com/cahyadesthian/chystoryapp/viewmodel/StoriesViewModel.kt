package com.cahyadesthian.chystoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.cahyadesthian.chystoryapp.model.InfoResponse
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.model.StoriesResponse
import com.cahyadesthian.chystoryapp.networking.Api
import com.cahyadesthian.chystoryapp.screen.util.Event
import com.cahyadesthian.chystoryapp.screen.util.StoryRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoriesViewModel(storyRepository: StoryRepository) : ViewModel(){

    val listStory = storyRepository.getStory().cachedIn(viewModelScope)

    class Factory(private val storyRepository: StoryRepository):
            ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StoriesViewModel(storyRepository) as T
        }
    }



}