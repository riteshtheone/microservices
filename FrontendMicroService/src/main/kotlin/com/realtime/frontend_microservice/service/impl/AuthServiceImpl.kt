package com.realtime.frontend_microservice.service.impl

import com.realtime.frontend_microservice.dto.UserDto
import com.realtime.frontend_microservice.external.service.UserService
import com.realtime.frontend_microservice.service.AuthService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(@Autowired private val userService: UserService) : AuthService {

    private val logger: Logger = LoggerFactory.getLogger(AuthServiceImpl::class.java)

    override fun createUser(userDto: UserDto): ResponseEntity<UserDto> = this.userService.createUser(userDto)

    override fun getUser(userId: Long): ResponseEntity<UserDto> = this.userService.getUser(userId)

    override fun getAllUsers(): ResponseEntity<Set<UserDto>> = this.userService.getAllUsers()
}