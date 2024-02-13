package com.example.todoapp.domain.todo.repository

import com.example.todoapp.domain.todo.model.QTodoEntity
import com.example.todoapp.domain.todo.model.TodoEntity
import com.example.todoapp.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class TodoRepositoryImpl:QueryDslSupport() {
    private val todo = QTodoEntity.todoEntity

    fun sortByDate():List<TodoEntity>{
        return queryFactory.selectFrom(todo)
           .orderBy(todo.creatAt.desc())
            .fetch()
    }
}