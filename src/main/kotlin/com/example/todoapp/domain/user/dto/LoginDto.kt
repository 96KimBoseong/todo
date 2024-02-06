package com.example.todoapp.domain.user.dto

data class LoginDto(
    val email:String,
    val password:String,
    val role: String
)
