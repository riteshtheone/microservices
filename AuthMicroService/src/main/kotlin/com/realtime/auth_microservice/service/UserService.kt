package com.realtime.auth_microservice.service

import com.realtime.auth_microservice.dto.UserDto

interface UserService {
    fun createUser(user: UserDto): UserDto
    fun getUser(userId: Long): UserDto
    fun getAllUsers(): Set<UserDto>
    fun deleteUser(userId: Long): Boolean
}