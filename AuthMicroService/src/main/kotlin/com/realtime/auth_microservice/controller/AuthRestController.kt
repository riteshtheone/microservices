package com.realtime.auth_microservice.controller

import com.realtime.auth_microservice.dto.UserDto
import com.realtime.auth_microservice.service.UserService

import jakarta.validation.Valid

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthRestController(
    @Autowired private val userService: UserService
    ) {
    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<UserDto> = ResponseEntity.ok(this.userService.getUser(userId))

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<Set<UserDto>> = ResponseEntity.ok(this.userService.getAllUsers())

    @PostMapping("/user")
    fun createUser(@Valid @RequestBody userDto: UserDto): ResponseEntity<UserDto> = ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userDto))

    @DeleteMapping("/user/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<Boolean> = ResponseEntity.ok(this.userService.deleteUser(userId))
}