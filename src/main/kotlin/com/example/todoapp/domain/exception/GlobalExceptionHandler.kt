package com.example.todoapp.domain.exception

import com.example.todoapp.domain.exception.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e:ModelNotFoundException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDto(message = e.message))
    }
}