package com.example.todoapp.domain.todo.controller

import com.example.todoapp.domain.comment.dto.CommentResponseDto
import com.example.todoapp.domain.todo.dto.RetrieveTodoDto
import com.example.todoapp.domain.todo.dto.TodoResponseDto
import com.example.todoapp.domain.todo.service.TodoService
import com.example.todoapp.infra.security.jwt.JwtPlugin
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.ints.shouldBeOdd
import io.kotest.matchers.shouldBe
import io.mockk.MockKException
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDateTime
import java.time.LocalTime

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class TodoControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin
): DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val todoService = mockk<TodoService>()

    describe("GET /todos/{todoId}"){
        context("todo를 조회할때"){
            it("200 status를 응답한다") {
                val todoId = 10L
                //val localTime = LocalDateTime.of(2024, 2, 8, 12, 0)
                every { todoService.getTodo(any(), any())} returns  RetrieveTodoDto(
                    todoId = todoId,
                    title = "test_title",
                    content = "test_content",
                    //createAt = localTime,
                    nickName = "test_name",
                    completed = false,
                    commentList = listOf()
                )
                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "qwe123@nate.com",
                    role = "USER"
                )
                val result = mockMvc.perform(
                    get("/todos/$todoId")
                        .header("Authorization","Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                result.response.status shouldBe 200

                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    RetrieveTodoDto::class.java
                )

                responseDto.todoId shouldBe todoId

            }

        }

    }
})