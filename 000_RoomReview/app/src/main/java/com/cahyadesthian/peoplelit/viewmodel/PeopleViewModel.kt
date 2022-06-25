package com.cahyadesthian.peoplelit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cahyadesthian.peoplelit.data.PeopleDatabase
import com.cahyadesthian.peoplelit.model.People
import com.cahyadesthian.peoplelit.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<People>>
    private val repository: PeopleRepository


    init {

        val peopleDao = PeopleDatabase.getDatabase(application).peopleDao()
        repository = PeopleRepository(peopleDao)
        readAllData = repository.readAllData

    }

    fun addPeople(people: People) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPeople(people)
        }
    }

}