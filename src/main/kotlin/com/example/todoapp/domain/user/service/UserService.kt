package com.example.todoapp.domain.user.service

import com.example.todoapp.domain.user.dto.LoginDto
import com.example.todoapp.domain.user.dto.LoginResponseDto
import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto

interface UserService {
    fun signUp(signUpDto: SignUpDto):UserResponseDto
    fun login(loginDto: LoginDto):LoginResponseDto

}