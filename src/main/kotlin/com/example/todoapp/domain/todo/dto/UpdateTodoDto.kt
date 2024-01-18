package com.example.todoapp.domain.todo.dto

data class UpdateTodoDto(
    val title:String,
    val content:String,
    val nickName:String
    //,val completed:Boolean
)
//업데이트용 데이터전송객체 요청 !