package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {   // Nothing : 아무 값도 반환하지 않고, 반드시 예외를 던진다는 걸 나타내는 특수 타입
    throw IllegalArgumentException()    // 커스텀 예외 처리 패턴
}

fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id: ID): T {  // CrudRepository에 대해 확장 메서드
    return this.findByIdOrNull(id) ?: fail()    // findByIdOrNull : Kotlin에서 Spring Data JPA를 쓸 때, nullable로 반환되는 버전
}