package com.example.todoapp.domain.todo.model

import com.example.todoapp.domain.todo.dto.TodoResponseDto
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class TodoEntity(
    @Column(name = "title")
    var title:String,
    @Column(name = "content")
    var content:String,
    @Column(name = "nick_name")
    var nickName:String,
    @Column(name = "create_at")
    var creatAt:LocalDateTime
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var todoId:Long? = null
}
fun TodoEntity.toResponse():TodoResponseDto{
    return TodoResponseDto(
        todoId = todoId!!,
        title = title,
        content = content,
        nickName = nickName,
        createAt = creatAt
    )
}
//엔티티 안의 값을 반화해줘야 하니까 확장함수를 쓴거임 dto안에 넣어주는거 반환용ㅇㅇ
//fun completed(){} 완료 여부
