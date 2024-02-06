package com.example.todoapp.domain.user.controller

import com.example.todoapp.domain.user.dto.LoginDto
import com.example.todoapp.domain.user.dto.LoginResponseDto
import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import com.example.todoapp.domain.user.service.UserService
import jakarta.validation.Valid
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
      @Valid
      @RequestBody signUpDto: SignUpDto
    ):ResponseEntity<UserResponseDto>{
       return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpDto))
    }
    @PostMapping("/Login")
    fun login(
        @Valid
        @RequestBody loginDto: LoginDto
    ):ResponseEntity<LoginResponseDto>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginDto))
    }
}