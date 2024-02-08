package com.example.todoapp.domain.todo.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CreateTodoDto(
    @field:Size(min = 10, max = 500, message = "500자 제한"
    )
    val title:String,
    @field:Size(min = 10, max = 5000, message = "5000자 제한"
    )
    val content:String,
    val createAt:LocalDateTime,
    val nickName:String
)
