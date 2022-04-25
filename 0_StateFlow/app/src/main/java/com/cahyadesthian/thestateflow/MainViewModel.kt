package com.cahyadesthian.thestateflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    //state flow
    private val _loginUiStete = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState : StateFlow<LoginUiState> = _loginUiStete

    //custom type to passed on stateflow type yang empty
    sealed class LoginUiState {
        object Success : LoginUiState()
        data class Error(val message: String) : LoginUiState()
        object Loading : LoginUiState()
        object Empty: LoginUiState()
    }


    fun login(username: String, password: String) = viewModelScope.launch {
        _loginUiStete.value = LoginUiState.Loading
        delay(2000L)
        if(username == "android" && password == "cahya") {
            _loginUiStete.value = LoginUiState.Success
        } else {
            _loginUiStete.value = LoginUiState.Error("Wrong Password \uD83E\uDD0F")
        }
    }

}