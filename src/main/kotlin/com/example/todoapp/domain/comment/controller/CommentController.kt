package com.example.todoapp.domain.comment.controller

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.CreateCommentDto
import com.example.todoapp.domain.comment.dto.DeleteCommentDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto
import com.example.todoapp.domain.comment.service.CommentService
import com.example.todoapp.infra.security.UserPrincipal
import org.apache.catalina.User
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    fun createComment(
        @RequestBody createCommentDto:CreateCommentDto,
        @AuthenticationPrincipal user:UserPrincipal
    ):ResponseEntity<CommentResponseDto>{
        val result = commentService.createComment(createCommentDto,user.id)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody updateCommentDto: UpdateCommentDto,
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<CommentResponseDto>{
        val updateCommentDtoArg = UpdateCommentDto(
            commentContent = updateCommentDto.commentContent,
            commentPassword = updateCommentDto.commentPassword,
            commentName = updateCommentDto.commentName
        )
        val comment = commentService.updateComment(commentId,updateCommentDtoArg,user.id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(comment)
    }
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable commentId: Long,
        @RequestBody deleteCommentDto: DeleteCommentDto,
        @AuthenticationPrincipal user:UserPrincipal
    ):ResponseEntity<Unit>{
        commentService.deleteComment(commentId,deleteCommentDto,user.id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }
















}//end
