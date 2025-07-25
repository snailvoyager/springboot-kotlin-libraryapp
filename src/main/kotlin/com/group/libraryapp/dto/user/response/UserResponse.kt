package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

data class UserResponse(
        val id: Long,
        val name: String,
        val age: Int?,
) {

    companion object {
        fun of(user: User): UserResponse {  // 정적 팩터리 메서드
            return UserResponse(
                    id = user.id!!,
                    name = user.name,
                    age = user.age,
            )
        }
    }

    /*constructor(user: User): this(
            id = user.id!!,
            name = user.name,
            age = user.age,
    )*/
    /*init {
        id = user.id!!
        name = user.name
        age = user.age
    }*/
}
