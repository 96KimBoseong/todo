package com.example.todoapp.domain.todo.dto

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.todo.model.TodoEntity
import java.time.LocalDateTime

data class RetrieveTodoDto(
    val todoId:Long,
    val title:String,
    val content:String,
    val createAt: LocalDateTime,
    val nickName:String,
    val completed:Boolean,
    val commentList:List<CommentResponseDto>
){
    companion object{
        fun from(todoEntity: TodoEntity):RetrieveTodoDto{
            return RetrieveTodoDto(
                todoId = todoEntity.todoId!!,
                title = todoEntity.title,
                content = todoEntity.content,
                createAt = todoEntity.creatAt,
                nickName = todoEntity.nickName,
                completed = todoEntity.completed,
                commentList = todoEntity.comments.map { CommentResponseDto.from(it) }
            )
        }
    }

}
