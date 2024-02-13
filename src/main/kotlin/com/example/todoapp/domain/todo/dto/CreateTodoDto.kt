package com.example.todoapp.domain.todo.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CreateTodoDto(
    @field:Size(min = 1, max = 500, message = "제목은 500자 제한입니다")
    val title:String,
    @field:Size(min = 1, max = 5000, message = "본문은 5000자 제한입니다")
    val content:String,
    val createAt:LocalDateTime,
    val nickName:String
)
