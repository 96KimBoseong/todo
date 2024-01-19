package com.example.todoapp.domain.user.repository

import com.example.todoapp.domain.user.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<UserEntity,Long> {
    fun existsByEmail(email:String):Boolean
}