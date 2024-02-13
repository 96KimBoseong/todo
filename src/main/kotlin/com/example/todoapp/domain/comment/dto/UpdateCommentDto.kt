package com.example.todoapp.domain.comment.dto

data class UpdateCommentDto(
    val commentContent:String,
    val commentName:String,
    val commentPassword:String
    //,
    //val todoId:Long
)
