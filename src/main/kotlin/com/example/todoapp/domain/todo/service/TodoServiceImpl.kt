package com.example.todoapp.domain.todo.service

import com.example.todoapp.domain.exception.ModelNotFoundException
import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto
import com.example.todoapp.domain.todo.model.TodoEntity
import com.example.todoapp.domain.todo.model.toResponse
import com.example.todoapp.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
):TodoService {// todoRepository의 객체를 주입받고 TodoService의 함수를 상속 받음
    override fun getTodo(todoId:Long):TodoResponseDto {
        val findTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        return findTodo.toResponse()
    }//하나의 투두리스트를 가져오는 함수구현부

    override fun getTodoList():List<TodoResponseDto> {
        return todoRepository.findAll().map{it.toResponse()}
    }

    @Transactional
    override fun createTodo(createTodoDto: CreateTodoDto):TodoResponseDto {
        return todoRepository.save(
            TodoEntity(
                title = createTodoDto.title,
                content = createTodoDto.content,
                creatAt = createTodoDto.createAt,
                nickName = createTodoDto.nickName
            )
        ).toResponse()
    }
    @Transactional
    override fun updateTodo(todoId: Long,updateTodoDto: UpdateTodoDto):TodoResponseDto {
        val updateTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
            updateTodo.title = updateTodoDto.title
            updateTodo.content = updateTodoDto.content
            updateTodo.nickName =  updateTodoDto.nickName
        return todoRepository.save(updateTodo).toResponse()
    }
    @Transactional
    override fun deleteTodo(todoId: Long) {
        val deleteTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        todoRepository.delete(deleteTodo)

    }
    @Transactional
    override fun completedTodo(todoId: Long):TodoResponseDto {
        val todoCompleted = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)

            todoCompleted?.let{
                it.complete()
                todoRepository.save(it)
            }
        return todoCompleted.toResponse()
    }
}