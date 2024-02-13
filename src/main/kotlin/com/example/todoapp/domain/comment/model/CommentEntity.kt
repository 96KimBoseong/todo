package com.example.todoapp.domain.comment.model

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto
import com.example.todoapp.domain.todo.model.TodoEntity
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var commentId:Long? = null,
    @Column(name = "comment_content")
    var commentContent:String,
    @Column(name = "comment_name")
    var commentName:String,
    @Column(name = "comment_password")
    var commentPassword:String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    var todo: TodoEntity,
    @Column(name = "user_id")
    var userId:Long

) {

    fun changContent(updateCommentDto: UpdateCommentDto){
        this.commentContent =updateCommentDto.commentContent
    }
    fun checkNameAndPassword(commentName: String,commentPassword: String){
        if(commentName != this.commentName){
            throw Exception("wrong")
        }
        if(commentPassword != this.commentPassword){
            throw Exception("wrong")
        }
    }
}
