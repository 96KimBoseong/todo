package com.example.todoapp.domain.user.service

import com.example.todoapp.domain.user.dto.LoginDto
import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import com.example.todoapp.domain.user.model.UserEntity
import com.example.todoapp.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
):UserService {
    @Transactional
    override fun signUp(signUpDto: SignUpDto): UserResponseDto {
        if (userRepository.existsByEmail(signUpDto.email)) throw IllegalStateException("가입된 이메일")
        return UserEntity.toUserResponse(userRepository.save(UserEntity.toUserEntity(signUpDto)))

    }
    override fun login(loginDto: LoginDto): UserResponseDto {
        TODO("Not yet implemented")
    }
}