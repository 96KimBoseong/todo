package com.example.todoapp.domain.comment.repository

import com.example.todoapp.domain.comment.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<CommentEntity,Long> {

}