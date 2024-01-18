package com.example.todoapp.domain.comment.controller

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.CreateCommentDto
import com.example.todoapp.domain.comment.dto.DeleteCommentDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto
import com.example.todoapp.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/todos/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun createComment(
        @RequestBody createCommentDto:CreateCommentDto
    ):ResponseEntity<CommentResponseDto>{
        val result = commentService.createComment(createCommentDto)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody updateCommentDto: UpdateCommentDto
    ):ResponseEntity<CommentResponseDto>{
        val updateCommentDtoArg = UpdateCommentDto(
            commentId = commentId,
            commentContent = updateCommentDto.commentContent,
            commentPassword = updateCommentDto.commentPassword,
            commentName = updateCommentDto.commentName
        )
        val comment = commentService.updateComment(commentId,updateCommentDtoArg)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(comment)
    }
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
        @RequestBody deleteCommentDto: DeleteCommentDto
    ):ResponseEntity<Unit>{
        val deleteCommentArg = DeleteCommentDto(
            commentId = deleteCommentDto.commentId,
            commentName = deleteCommentDto.commentName,
            commentPassword = deleteCommentDto.commentPassword
        )
        commentService.deleteComment(commentId,deleteCommentArg)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
















}//end
