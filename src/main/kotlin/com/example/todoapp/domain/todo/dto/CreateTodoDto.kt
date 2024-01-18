package com.example.todoapp.domain.todo.dto

import java.time.LocalDateTime

data class CreateTodoDto(
    val title:String,
    val content:String,
    val createAt:LocalDateTime,
    val nickName:String

    //, val completed:Boolean
)
//todolist를 만들때 필요한 데이터전송객체 요청!