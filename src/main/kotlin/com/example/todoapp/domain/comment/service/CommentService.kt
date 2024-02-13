package com.example.todoapp.domain.comment.service

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.CreateCommentDto
import com.example.todoapp.domain.comment.dto.DeleteCommentDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto

interface CommentService {
    //fun getCommentById(commentId:Long):CommentResponseDto
    //fun getCommentListById():List<CommentResponseDto>
    fun createComment(createCommentDto: CreateCommentDto,userId:Long):CommentResponseDto
    fun updateComment(commentId: Long,updateCommentDto: UpdateCommentDto,userId:Long):CommentResponseDto
    fun deleteComment(commentId: Long,deleteCommentDto: DeleteCommentDto,userId:Long)
}