package com.example.todoapp.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.apache.commons.lang3.CharSet
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date

@Component
class JwtPlugin {
    companion object{
        const val SECRET = "PO4c8z41Hia5gJG3oeuFJMRYBB4Ws4aZ"
        const val ISSUER = "team.sparta.com"
        const val ACCESS_TOKEN_EXPIRATION_HOUR:Long = 168
    }//yml에 빼주면 되는데 지금 작성 안되있어서 패쓰
    fun validateToken(jwt:String): Result<Jws<Claims>>{
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))

            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject:String,email:String,role:String):String{
        return generateToken(subject,email,role,Duration.ofHours(ACCESS_TOKEN_EXPIRATION_HOUR))
    }

    private fun generateToken(subject:String,email:String,role:String,expirationPeriod:Duration):String{

        val claims:Claims = Jwts.claims()
            .add(mapOf("role" to role,"email" to email))
            .build()

        val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()


        return Jwts.builder()
            .subject(subject)
            .issuer(ISSUER)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }

    fun cookieToken(response:HttpServletResponse,jwt: String){
        val cookie = Cookie("cookie",jwt)
        cookie.path ="/"
        cookie.isHttpOnly = true
        cookie.secure = true
        response.addCookie(cookie)
    }
}