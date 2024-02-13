package com.example.todoapp.domain.todo.repository

import com.example.todoapp.domain.todo.model.TodoEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CustomTodoRepository {
    fun sortByDate():List<TodoEntity>
}