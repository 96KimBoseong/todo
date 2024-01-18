package com.example.todoapp.domain.user.controller

import com.example.todoapp.domain.user.dto.LoginDto
import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import com.example.todoapp.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/users")
@RestController
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signUp")
    fun signUp(
      @RequestBody signUpDto: SignUpDto
    ):ResponseEntity<UserResponseDto>{
       return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpDto))
    }
    @PostMapping("/Login")
    fun login(
        @RequestBody loginDto: LoginDto
    ):ResponseEntity<UserResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginDto))
    }
}