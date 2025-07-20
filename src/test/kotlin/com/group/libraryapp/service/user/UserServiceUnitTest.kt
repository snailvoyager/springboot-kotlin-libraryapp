package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.util.findByIdOrThrow
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class UserServiceUnitTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var userService: UserService

    @Test
    fun saveUserTest() {
        val request = UserCreateRequest("name", null)
        every { userRepository.save(any()) } returns User("A", 20, mutableListOf(), 1L)

        userService.saveUser(request)

        verify { userRepository.save(any()) }
    }

    @Test
    fun getUsersTest() {
        every { userRepository.findAll() } returns listOf(User("A", 20, mutableListOf(), 1L),
            User("B", 30, mutableListOf(), 2L),)

        val users = userService.getUsers()

        assertThat(users).hasSize(2)
        assertThat(users).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(users).extracting("age").containsExactlyInAnyOrder(20, 30)
        verify { userRepository.findAll() }
    }

    @Test
    fun updateUserNameTest() {
        val request = UserUpdateRequest(1L, "B")
        every { userRepository.findByIdOrThrow(request.id) } returns User("A", 20, mutableListOf(), 1L)

        userService.updateUserName(request)

        verify { userRepository.findByIdOrThrow(request.id) }
    }

    @Test
    fun updateUserNameTestThenFail() {
        val request = UserUpdateRequest(1L, "B")
        every { userRepository.findByIdOrThrow(request.id) } throws IllegalArgumentException()

        assertThatThrownBy {
            userService.updateUserName(request)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

}