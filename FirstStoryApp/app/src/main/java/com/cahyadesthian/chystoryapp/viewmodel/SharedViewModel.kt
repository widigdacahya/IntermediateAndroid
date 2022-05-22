package com.cahyadesthian.chystoryapp.viewmodel

import androidx.lifecycle.*
import com.cahyadesthian.chystoryapp.screen.util.SessionDataPreference
import kotlinx.coroutines.launch

class SharedViewModel(private val sessionPref: SessionDataPreference): ViewModel() {

    fun seeToken(): LiveData<String> {
        return sessionPref.getStoredToken().asLiveData()
    }

    fun storeToken(userToken: String) {
        viewModelScope.launch {
            sessionPref.storeToken(userToken)
        }
    }

    class Factory(private val sessionDataPref: SessionDataPreference): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SharedViewModel(sessionDataPref) as T
        }

    }

}