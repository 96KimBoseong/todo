package com.example.todoapp.domain.comment.dto

data class UpdateCommentDto(
    val commentId:Long?,
    val commentContent:String,
    val commentName:String,
    val commentPassword:String
    //,
    //val todoId:Long
)
