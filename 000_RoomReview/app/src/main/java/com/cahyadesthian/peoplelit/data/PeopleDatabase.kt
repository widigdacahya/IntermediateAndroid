package com.cahyadesthian.peoplelit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [People::class], version = 1, exportSchema = false)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao

    companion object{
        @Volatile
        private var DB_INSTANCE: PeopleDatabase? = null

        fun getDatabase(context: Context): PeopleDatabase{
            val tempInstance = DB_INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleDatabase::class.java,
                    "people_database"
                ).build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }

}