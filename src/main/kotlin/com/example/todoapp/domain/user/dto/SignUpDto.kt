package com.example.todoapp.domain.user.dto

data class SignUpDto(
    val name:String,
    val password:String,
    val email:String,
    val role:String
)
