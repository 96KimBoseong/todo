package com.example.todoapp.domain.user.model

import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import jakarta.persistence.*
import org .springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "app_user")
class UserEntity(
   @Column(name = "name")
    var name:String,
    @Column(name = "email")
    var email:String,
    @Column(name = "password")
    var password:String,
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null
    companion object{
//        fun toUserEntity(
//            signUpDto: SignUpDto,
//            passwordEncoder: PasswordEncoder
//        ):UserEntity{
//            return UserEntity(
//                name= signUpDto.name,
//                email = signUpDto.email,
//                password = passwordEncoder.encode(signUpDto.password),
//                role = signUpDto.role
//            )
//        }
        fun UserEntity.toUserResponse(
        ):UserResponseDto{
            return UserResponseDto(
                userId = userId!!,
                email = email,
                name = name

            )
        }
    }
}
