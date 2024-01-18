package com.example.todoapp.domain.user.model

import com.example.todoapp.domain.user.dto.SignUpDto
import com.example.todoapp.domain.user.dto.UserResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class UserEntity(
   @Column(name = "name")
    var name:String,
    @Column(name = "email")
    var email:String,
    @Column(name = "password")
    var password:String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null
    companion object{
        fun toUserEntity(
            signUpDto: SignUpDto
        ):UserEntity{
            return UserEntity(
                name= signUpDto.name,
                email = signUpDto.email,
                password = signUpDto.password
            )
        }
        fun toUserResponse(
            userEntity: UserEntity
        ):UserResponseDto{
            return UserResponseDto(
                userId = userEntity.userId!!,
                email = userEntity.email,
                name = userEntity.name
            )
        }
    }
}
