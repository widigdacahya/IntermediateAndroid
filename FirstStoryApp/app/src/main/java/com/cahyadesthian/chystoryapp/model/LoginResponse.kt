package com.cahyadesthian.chystoryapp.model

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val loginResult: LoginResult
)
