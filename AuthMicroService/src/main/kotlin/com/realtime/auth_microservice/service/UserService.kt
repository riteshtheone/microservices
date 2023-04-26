package com.realtime.auth_microservice.service

import com.realtime.auth_microservice.dto.UserDto

interface UserService {
    fun createUser(userDto: UserDto): UserDto
    fun updateUser(userDto: UserDto, userId: Long): UserDto
    fun getUser(userId: Long): UserDto
    fun getAllUsers(): Set<UserDto>
    fun deleteUser(userId: Long): Boolean
}