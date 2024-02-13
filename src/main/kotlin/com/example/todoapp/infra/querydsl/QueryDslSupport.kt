package com.example.todoapp.infra.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.annotation.Persistent

abstract class QueryDslSupport {

    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    protected val queryFactory:JPAQueryFactory
        get(){
            return JPAQueryFactory(entityManager)
        }
}