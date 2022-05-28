package com.cahyadesthian.chystoryapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cahyadesthian.chystoryapp.model.ItemListStory

@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<ItemListStory>)

    @Query("SELECT * FROM story_item")
    fun getAllStory(): PagingSource<Int, ItemListStory>

    @Query("DELETE FROM story_item")
    suspend fun deleteAllStory()

}