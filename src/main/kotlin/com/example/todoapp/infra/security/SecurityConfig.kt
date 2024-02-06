package com.example.todoapp.infra.security

import com.example.todoapp.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity//http 기반으로 통신을 할 때 관련 보안기능을 설정 을 할때 다는 어노테이션
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler:CustomAccessDeniedHandler,
) {
    @Bean
    fun filterChain(http:HttpSecurity):SecurityFilterChain{
        return http
            .httpBasic{it.disable()}
            .formLogin{it.disable()}
            .csrf{it.disable()}
            .authorizeHttpRequests{
                it.requestMatchers(
                    "/users/Login",
                    "/users/signUp",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    //위에 경로를 인증에서 제외한다
                ).permitAll()
                 .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling{
                it.authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)
            }// 테스트할때 주석 쳐보고 할 것
            .build()
    }
}
//filter 요상한거 끄기