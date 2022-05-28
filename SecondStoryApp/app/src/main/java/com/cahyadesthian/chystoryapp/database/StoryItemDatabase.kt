package com.cahyadesthian.chystoryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cahyadesthian.chystoryapp.model.ItemListStory

@Database(entities = [ItemListStory::class, RemoteKeyEntity::class], version=1)
abstract class StoryItemDatabase : RoomDatabase() {

    abstract fun getStoryDao(): StoryDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: StoryItemDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StoryItemDatabase {
            return INSTANCE?: Room.databaseBuilder(context.applicationContext, StoryItemDatabase::class.java,"item_story_database")
                .build().also {
                    INSTANCE = it
                }
        }



    }

}