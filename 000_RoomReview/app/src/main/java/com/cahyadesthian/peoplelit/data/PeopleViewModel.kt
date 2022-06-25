package com.cahyadesthian.peoplelit.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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