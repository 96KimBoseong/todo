package com.example.todoapp.domain.todo.repository

import com.example.todoapp.domain.todo.model.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository:JpaRepository<TodoEntity,Long> {
}