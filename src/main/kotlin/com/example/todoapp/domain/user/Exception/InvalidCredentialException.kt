package com.example.todoapp.domain.user.Exception

data class InvalidCredentialException(
    override val message:String? = "the credential is invalid"
):RuntimeException()
