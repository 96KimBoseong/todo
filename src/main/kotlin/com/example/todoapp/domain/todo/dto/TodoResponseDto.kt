package com.example.todoapp.domain.todo.dto

import java.time.LocalDateTime

data class TodoResponseDto(
    val todoId:Long,
    val title:String,
    val content:String,
    val createAt:LocalDateTime,
    val nickName:String,
    val completed:Boolean
    )
//반환해주는 데이터 전송 객체