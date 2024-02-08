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
        @AuthenticationPrincipal user: UserPrincipal
    ):ResponseEntity<RetrieveTodoDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodo(todoId,user.id))
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
        @Valid@RequestBody createTodoDto: CreateTodoDto,
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
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
