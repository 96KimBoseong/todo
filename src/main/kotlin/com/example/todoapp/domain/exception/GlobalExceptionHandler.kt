package com.example.todoapp.domain.exception

import com.example.todoapp.domain.exception.dto.ErrorResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e:ModelNotFoundException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponseDto(e.message))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponseDto(e.message))
    }
    @ExceptionHandler(IllegalStateException::class)
    fun handlerIllegalStateException(e:IllegalStateException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponseDto(e.message))
    }
    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerIllegalArgumentException(e:IllegalArgumentException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto(e.message))
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(e:MethodArgumentNotValidException):ResponseEntity<ErrorResponseDto>{
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponseDto("${e.bindingResult.fieldErrors.first().defaultMessage}"))
    }
    @ExceptionHandler(CustomException::class)
    fun handlePasswordException(e: CustomException): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto(message = e.message))
    }

}
