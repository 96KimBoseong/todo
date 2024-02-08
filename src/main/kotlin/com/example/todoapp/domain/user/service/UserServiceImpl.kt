package com.example.todoapp.domain.user.service

import com.example.todoapp.domain.exception.InvalidCredentialException
import com.example.todoapp.domain.user.dto.LoginDto
import com.example.todoapp.domain.user.dto.LoginResponseDto
import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import com.example.todoapp.domain.user.model.UserEntity
import com.example.todoapp.domain.user.model.UserEntity.Companion.toUserResponse
import com.example.todoapp.domain.user.model.UserRole
import com.example.todoapp.domain.user.repository.UserRepository
import com.example.todoapp.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
):UserService {
    @Transactional
    override fun signUp(signUpDto: SignUpDto): UserResponseDto {
        if (userRepository.existsByEmail(signUpDto.email)) throw IllegalStateException("가입된 이메일")
        //return UserEntity.toUserResponse(userRepository.save(UserEntity.toUserEntity(signUpDto,passwordEncoder)))
        val role = when(signUpDto.role){
            "ADMIN" -> UserRole.ADMIN
            "USER" -> UserRole.USER
            else -> throw IllegalArgumentException("Invalid role")
        }
        return userRepository.save(
            UserEntity(
                name = signUpDto.name,
                email = signUpDto.email,
                password = passwordEncoder.encode(signUpDto.password),
                role = role
            )
        ).toUserResponse()
    }
//
override fun login(loginDto: LoginDto): LoginResponseDto {
    val userFind = userRepository.findByEmail(loginDto.email) ?: throw IllegalStateException("사용자를 찾을 수 없습니다")

    if (userFind.role.name == loginDto.role && passwordEncoder.matches(loginDto.password, userFind.password)) {
        return LoginResponseDto(
            accessToken = jwtPlugin.generateAccessToken(
                subject = userFind.userId.toString(),
                email = userFind.email,
                role = userFind.role.name
            )
        )
    } else {
        throw InvalidCredentialException("닉네임 또는 패스워드를 확인해주세요")
    }
 }
}
//userPrincipal을 인자값으로 받고 userPrincipal.id 이런식으로 사용가능