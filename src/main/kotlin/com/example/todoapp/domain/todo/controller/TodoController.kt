package com.example.todoapp.domain.todo.controller

import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto
import com.example.todoapp.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/{todoId}")
    fun getTodo(
        @PathVariable todoId:Long
    ):ResponseEntity<TodoResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodo(todoId))
    }
    //todoId를 가지고 데이터 베이스에 접근할꺼고 반환형을 ResponseEntity를 사용해서 TodoResponseDto로 반환할꺼임
    //실제로 보내는 부분은 ResponseEntity를 사용하여 상태코드는 OK 클라이언트가 받게될 응답의 본문todoId를 이용한 Impl에있는 함수를 실행한 값?
    @GetMapping()
    fun getTodoList():ResponseEntity<List<TodoResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList())
    }
    //위와 같으나 리스트로 반환
    @PostMapping
    fun createTodo(
        @RequestBody createTodoDto: CreateTodoDto
    ):ResponseEntity<TodoResponseDto>
    {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoDto))
    }
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId:Long,
        @RequestBody updateTodoDto: UpdateTodoDto
    ):ResponseEntity<TodoResponseDto>
    {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoDto))
    }
    @PutMapping("/{todoId}/complete")
    fun completedTodo(
        @PathVariable todoId:Long
    ):ResponseEntity<TodoResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.completedTodo(todoId))
    }
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @PathVariable todoId: Long,
    ):ResponseEntity<Unit>
    {
        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}