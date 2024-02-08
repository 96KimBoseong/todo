package com.example.todoapp.domain.user.dto

import jakarta.validation.constraints.Pattern

data class SignUpDto(
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{3,}$",
        message = "이름은 3자이상 알파벳 대소문자 및 숫자로 구성"
    )
    val name:String,

    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{4,}$",
        message = "비밀번호는 4자이상 알파벳 대소문자 및 숫자로 구성"
    )
    val password:String,

    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{4,}$",
        message = "비밀번호는 4자이상 알파벳 대소문자 및 숫자로 구성"

    )
    val passwordConfirm:String,
    val email:String,
    val role:String
)
{
   // fun password 확인
    //companion object - 인스턴스화시켜서 바로 호출안하고 바로 사용
}