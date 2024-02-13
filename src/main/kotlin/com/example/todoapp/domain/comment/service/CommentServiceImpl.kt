package com.example.todoapp.domain.comment.service
import com.example.todoapp.domain.comment.model.CommentEntity
import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.comment.dto.CreateCommentDto
import com.example.todoapp.domain.comment.dto.DeleteCommentDto
import com.example.todoapp.domain.comment.dto.UpdateCommentDto
import com.example.todoapp.domain.comment.repository.CommentRepository
import com.example.todoapp.domain.exception.ModelNotFoundException
import com.example.todoapp.domain.todo.repository.TodoRepository
import com.example.todoapp.domain.user.model.UserEntity
import com.example.todoapp.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val todoRepository: TodoRepository,
    val userRepository: UserRepository
):CommentService {

    @Transactional
    override fun createComment( createCommentDto: CreateCommentDto,userId:Long):CommentResponseDto {
        val findTodoId = todoRepository.findByIdOrNull(createCommentDto.todoId)?: throw ModelNotFoundException("todo not found",createCommentDto.todoId)
        val commentEntity= CommentEntity(
            commentContent = createCommentDto.commentContent,
            commentName = createCommentDto.commentName,
            commentPassword = createCommentDto.commentPassword,
            todo = findTodoId,
            userId = userId
            //일단 야매로 ㅋㅋ
        )
        val result = commentRepository.save(commentEntity)
        return CommentResponseDto.from(result)
    }
    @Transactional
    override fun updateComment( commentId: Long, updateCommentDto: UpdateCommentDto,userId:Long):CommentResponseDto {
        val findComment =commentRepository.findByIdOrNull(commentId)?: throw ModelNotFoundException("comment",commentId)
        val findUser = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("user",userId)
        if(findComment.userId == findUser.userId){
            findComment.checkNameAndPassword(updateCommentDto.commentName,updateCommentDto.commentPassword)
            findComment.changContent(updateCommentDto)
            commentRepository.save(findComment)
            return CommentResponseDto.from(findComment)
        }else{
            throw IllegalArgumentException("사용자가 일치하지 않습니다")
        }

    }
    @Transactional
    override fun deleteComment( commentId: Long,deleteCommentDto: DeleteCommentDto,userId:Long) {
        val findComment = commentId?.let{commentRepository.findByIdOrNull(it)}?:throw Exception("comment not found")
        val findUser = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("user",userId)

        if(findComment.userId == findUser.userId){
            findComment.checkNameAndPassword(deleteCommentDto.commentName,deleteCommentDto.commentPassword)
            commentRepository.deleteById(commentId)
        }else{
            throw IllegalArgumentException("사용자가 일치하지 않습니다")
        }

        }
    }
