package com.example.todoapp.domain.comment.dto

data class DeleteCommentDto(
    val commentId:Long,
    val commentName:String,
    val commentPassword:String
)
