package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {

    fun findByName(name: String): User?

//    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories") // fetch join을 통해서 연관 엔티티 즉시 함께 조회
//    fun findAllWithHistories(): List<User>
}