package com.example.todoapp.domain.exception

data class InvalidCredentialException(
    override val message:String? = "the credential is invalid"
):RuntimeException()
