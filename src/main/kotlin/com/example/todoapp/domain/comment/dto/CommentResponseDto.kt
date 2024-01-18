package com.example.todoapp.domain.comment.dto

import com.example.todoapp.domain.comment.model.CommentEntity

data class CommentResponseDto(
    val commentId:Long,
    val commentContent:String,
    val commentName:String,
    val todoId:Long

){
    companion object{
        fun from(commentEntity: CommentEntity):CommentResponseDto{
            return CommentResponseDto(
                commentId = commentEntity.commentId!!,
                commentContent = commentEntity.commentContent,
                commentName = commentEntity.commentName,
                todoId = commentEntity.todo.todoId?: throw Exception("target todo is not persisted")
            )
        }
    }
}
