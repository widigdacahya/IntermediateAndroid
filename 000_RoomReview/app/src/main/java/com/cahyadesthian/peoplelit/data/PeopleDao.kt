package com.cahyadesthian.peoplelit.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cahyadesthian.peoplelit.model.People

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPeople(people: People)

    @Query("SELECT * FROM people_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<People>>




}