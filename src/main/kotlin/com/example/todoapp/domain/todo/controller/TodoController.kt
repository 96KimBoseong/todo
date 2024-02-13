package com.example.todoapp.domain.todo.controller

import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.RetrieveTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto
import com.example.todoapp.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.todoapp.infra.security.UserPrincipal
import jakarta.validation.Valid
import org.hibernate.annotations.Fetch
import org.springframework.validation.annotation.Validated

@RequestMapping("/todos")
@RestController
@Validated
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/{todoId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun getTodo(
        @PathVariable todoId:Long,
    ):ResponseEntity<RetrieveTodoDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodo(todoId))
    }

    @GetMapping()
    fun getTodoList():ResponseEntity<List<TodoResponseDto>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList())
    }

    @PostMapping
    fun createTodo(
        @Valid @RequestBody createTodoDto: CreateTodoDto,
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<TodoResponseDto>
    {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(createTodoDto, user.id))
    }
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId:Long,
        @RequestBody updateTodoDto: UpdateTodoDto,
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<TodoResponseDto>
    {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId,updateTodoDto, user.id))
    }
    @PutMapping("/{todoId}/complete")
    fun completedTodo(
        @PathVariable todoId:Long,
        @AuthenticationPrincipal user:UserPrincipal
    ):ResponseEntity<TodoResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.completedTodo(todoId, user.id))
    }
    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    fun deleteTodo(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal user:UserPrincipal
    ):ResponseEntity<Unit>
    {
        todoService.deleteTodo(todoId,user.id)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }



}
