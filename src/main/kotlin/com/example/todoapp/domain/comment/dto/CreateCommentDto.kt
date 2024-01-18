package com.example.todoapp.domain.comment.dto

import jakarta.persistence.Id

data class CreateCommentDto(
    val commentContent:String,
    val commentName:String,
    val commentPassword:String,
    val todoId: Long
)
