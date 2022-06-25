package com.cahyadesthian.peoplelit.repository

import androidx.lifecycle.LiveData
import com.cahyadesthian.peoplelit.data.PeopleDao
import com.cahyadesthian.peoplelit.model.People

class PeopleRepository(private val peopleDao: PeopleDao) {

    val readAllData: LiveData<List<People>> = peopleDao.readAllData()

    suspend fun addPeople(people: People) {
        peopleDao.addPeople(people)
    }

}