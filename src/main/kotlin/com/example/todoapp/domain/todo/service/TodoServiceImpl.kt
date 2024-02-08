package com.example.todoapp.domain.todo.service

import com.example.todoapp.domain.comment.repository.CommentRepository
import com.example.todoapp.domain.exception.CustomException
import com.example.todoapp.domain.exception.ModelNotFoundException
import com.example.todoapp.domain.todo.dto.CreateTodoDto
import com.example.todoapp.domain.todo.dto.RetrieveTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.dto.UpdateTodoDto
import com.example.todoapp.domain.todo.model.TodoEntity
import com.example.todoapp.domain.todo.model.toResponse
import com.example.todoapp.domain.todo.repository.TodoRepository
import com.example.todoapp.domain.user.model.UserEntity
import com.example.todoapp.domain.user.repository.UserRepository
import com.example.todoapp.infra.security.UserPrincipal
import jakarta.persistence.Id
import org.apache.catalina.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
):TodoService {// todoRepository의 객체를 주입받고 TodoService의 함수를 상속 받음
    override fun getTodo(todoId:Long,userId: Long):RetrieveTodoDto? {
        val findTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        findTodo.user

        return findTodo?.let { RetrieveTodoDto.from(it) }
    }//principal 빼기
    //

    override fun getTodoList():List<TodoResponseDto> {
        return todoRepository.findAll().map{it.toResponse()}
    }

    @Transactional
    override fun createTodo(createTodoDto: CreateTodoDto,userId: Long):TodoResponseDto {
        val users = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("user",userId)
        return todoRepository.save(
            TodoEntity(
                title = createTodoDto.title,
                content = createTodoDto.content,
                creatAt = createTodoDto.createAt,
                nickName = createTodoDto.nickName,
                user = users
            )
        ).toResponse()
    }//검증하고
    @Transactional
    override fun updateTodo(todoId: Long,updateTodoDto: UpdateTodoDto,userId: Long):TodoResponseDto {
        val updateTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        val users = userRepository.findByIdOrNull(userId)?: throw  ModelNotFoundException("user",userId)
        if (updateTodo.user !=users){
            throw CustomException("dsad")
            //에러 순서 커스텀 -> 필터 -> 사용자 Exception
        }else{
            updateTodo.title = updateTodoDto.title
            updateTodo.content = updateTodoDto.content
            updateTodo.nickName =  updateTodoDto.nickName
            return todoRepository.save(updateTodo).toResponse()
        }
        //핸들링 따로 해줘야하나 ? jwt 에러가 전역으로 박혀있어서 안해도되나?
    }
    @Transactional
    override fun deleteTodo(todoId: Long,userId: Long) {
        val deleteTodo = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        val users = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("user",userId)
        if (deleteTodo.user != users){
            throw Exception("sss")
        }else{
            todoRepository.delete(deleteTodo)
        }

    }
    @Transactional
    override fun completedTodo(todoId: Long, userId: Long):TodoResponseDto {
        val todoCompleted = todoRepository.findByIdOrNull(todoId)?: throw ModelNotFoundException("todo",todoId)
        val users = userRepository.findByIdOrNull(userId)?:throw ModelNotFoundException("user",userId)
        if(todoCompleted.user != users){
            throw Exception("dsad")
        }else{
            todoCompleted?.let{
                it.complete()
                todoRepository.save(it)
            }
            return todoCompleted.toResponse()
        }

    }
}
//Exception 뭐던져야하지?