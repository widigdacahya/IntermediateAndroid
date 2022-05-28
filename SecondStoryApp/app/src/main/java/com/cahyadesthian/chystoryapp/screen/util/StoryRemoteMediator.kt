package com.cahyadesthian.chystoryapp.screen.util


import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cahyadesthian.chystoryapp.database.RemoteKeyEntity
import com.cahyadesthian.chystoryapp.database.StoryItemDatabase
import com.cahyadesthian.chystoryapp.model.ItemListStory
import com.cahyadesthian.chystoryapp.networking.ApiService


@OptIn(ExperimentalPagingApi::class)
class StoryRemoteMediator(
    private val database: StoryItemDatabase,
    private val apiService: ApiService,
    private val authThings: String) : RemoteMediator<Int, ItemListStory>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemListStory>
    ): MediatorResult {

        val page = when(loadType) {

            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPos(state)
                remoteKey?.nextKey?.minus(1)?: INITIAL_PAGE_INDEX
            }

            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForTheFirstItem(state)
                remoteKey?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }

            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForTheLastItem(state)
                remoteKey?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
            }

        }


        Log.d("StoryMediator", "load: $page ")


        try {
            val response = apiService.getPagedAllStory(authThings,page,state.config.pageSize).listStory

            val endPagination = response.isEmpty()


            database.withTransaction {

                if(loadType == LoadType.REFRESH) {
                    database.getRemoteKeyDao().deleteAllKey()
                    database.getStoryDao().deleteAllStory()
                }

                val keys = response.map {
                    RemoteKeyEntity(
                        id = it.id,
                        prevKey =  if( page == 1 ) null else page-1,
                        nextKey = if(endPagination) null else page+1
                    )
                }

                database.getRemoteKeyDao().insertRemoteKey(keys)
                database.getStoryDao().insertStories(response)

            }
            return MediatorResult.Success(endOfPaginationReached = endPagination)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }


    private suspend fun getRemoteKeyClosestToCurrentPos(state: PagingState<Int,ItemListStory>) =
        state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let {
                database.getRemoteKeyDao().getRemoteKeyId(it)
            }
        }

    private suspend fun getRemoteKeyForTheFirstItem(state: PagingState<Int, ItemListStory>) =
        state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { itemListStory ->
            database.getRemoteKeyDao().getRemoteKeyId(itemListStory.id)
        }

    private suspend fun getRemoteKeyForTheLastItem(state: PagingState<Int, ItemListStory>) =
        state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { itemListStory ->
            database.getRemoteKeyDao().getRemoteKeyId(itemListStory.id)
        }


    companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}