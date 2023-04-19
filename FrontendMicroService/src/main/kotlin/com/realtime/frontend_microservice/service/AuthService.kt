package com.realtime.frontend_microservice.service

import com.realtime.frontend_microservice.dto.UserDto

import org.springframework.http.ResponseEntity

interface AuthService {
    fun createUser(userDto: UserDto): ResponseEntity<UserDto>
    fun getUser(userId: Long): ResponseEntity<UserDto>

    fun getAllUsers(): ResponseEntity<Set<UserDto>>
}