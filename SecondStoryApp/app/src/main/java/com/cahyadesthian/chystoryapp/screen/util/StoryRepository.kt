package com.cahyadesthian.chystoryapp.screen.util

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.cahyadesthian.chystoryapp.database.StoryItemDatabase
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.networking.ApiService

class StoryRepository(private val database: StoryItemDatabase,private val apiService: ApiService, private val authThings: String) {

    @OptIn(ExperimentalPagingApi::class)
    fun getStory(): LiveData<PagingData<ItemListStory>> {

        return Pager(
            config = PagingConfig(pageSize = 5),
            remoteMediator = StoryRemoteMediator(database,apiService,authThings),
            pagingSourceFactory = {
                database.getStoryDao().getAllStory()
            }).liveData

    }

}