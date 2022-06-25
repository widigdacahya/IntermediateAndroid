package com.cahyadesthian.peoplelit.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cahyadesthian.peoplelit.model.People

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPeople(people: People)

    @Query("SELECT * FROM people_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<People>>

    @Update
    suspend fun updatePeople(people: People)

    @Delete
    suspend fun deletePeople(people: People)

    @Query("DELETE FROM people_table")
    suspend fun deleteAllPeople()


}