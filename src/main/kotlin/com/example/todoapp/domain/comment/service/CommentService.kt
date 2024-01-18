package com.example.todoapp.domain.comment.service

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.CreateCommentDto
import com.example.todoapp.domain.comment.dto.DeleteCommentDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto

interface CommentService {
    //fun getCommentById(commentId:Long):CommentResponseDto
    //fun getCommentListById():List<CommentResponseDto>
    fun createComment(createCommentDto: CreateCommentDto):CommentResponseDto
    fun updateComment(commentId: Long,updateCommentDto: UpdateCommentDto):CommentResponseDto
    fun deleteComment(commentId: Long,deleteCommentDto: DeleteCommentDto)
}