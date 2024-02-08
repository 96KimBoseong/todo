package com.example.todoapp.domain.todo.model

import com.example.todoapp.domain.comment.model.CommentEntity
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.user.model.UserEntity
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
    var creatAt:LocalDateTime,
    @OneToMany(mappedBy="todo")
    val comments:List<CommentEntity> = emptyList(),
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user:UserEntity
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var todoId:Long? = null
    @Column(name = "completed")
    private  var iscompleted:Boolean = false
    val completed:Boolean
        get() = iscompleted
    fun complete(){
       iscompleted = true
    }


    //외부에서 접근 불가하게 하려고 이렇게 만들었다는데 이해안됨 문법부족 ;;
}
fun TodoEntity.toResponse():TodoResponseDto{
    return TodoResponseDto(
        todoId = todoId!!,
        title = title,
        content = content,
        nickName = nickName,
        createAt = creatAt,
        completed = completed
    )
}
//엔티티 안의 값을 반화해줘야 하니까 확장함수를 쓴거임 dto안에 넣어주는거 반환용ㅇㅇ
//fun completed(){} 완료 여부
