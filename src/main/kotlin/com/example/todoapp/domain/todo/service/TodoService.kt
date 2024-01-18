package com.example.todoapp.domain.todo.service

import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto

interface TodoService {
    fun getTodo(todoId:Long):TodoResponseDto
    fun getTodoList():List<TodoResponseDto>
    fun createTodo(createTodoDto: CreateTodoDto):TodoResponseDto
    fun updateTodo(todoId: Long,updateTodoDto: UpdateTodoDto):TodoResponseDto
    fun deleteTodo(todoId: Long)
    fun completedTodo(todoId: Long):TodoResponseDto


}
//내가 쓸 함수 정의 해놓고 모아두기