package com.cahyadesthian.peoplelit.data

import androidx.lifecycle.LiveData

class PeopleRepository(private val peopleDao: PeopleDao) {

    val readAllData: LiveData<List<People>> = peopleDao.readAllData()

    suspend fun addPeople(people: People) {
        peopleDao.addPeople(people)
    }

}