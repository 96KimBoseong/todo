package com.example.todoapp.domain.todo.service

import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.RetrieveTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto
import com.example.todoapp.infra.security.UserPrincipal

interface TodoService {
    fun getTodo(todoId:Long):RetrieveTodoDto?
    fun getTodoList():List<TodoResponseDto>
    fun createTodo(createTodoDto: CreateTodoDto,userId: Long):TodoResponseDto
    fun updateTodo(todoId: Long,updateTodoDto: UpdateTodoDto,userId: Long):TodoResponseDto
    fun deleteTodo(todoId: Long,userId: Long)
    fun completedTodo(todoId: Long,userId:Long):TodoResponseDto


}
//내가 쓸 함수 정의 해놓고 모아두기