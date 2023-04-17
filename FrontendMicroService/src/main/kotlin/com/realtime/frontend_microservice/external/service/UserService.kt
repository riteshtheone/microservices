package com.realtime.frontend_microservice.external.service

import com.realtime.frontend_microservice.dto.UserDto

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "AUTH-MICROSERVICE")
interface UserService {

    @GetMapping("/api/user/{userId}")
    fun getUser(@PathVariable userId: Long): ResponseEntity<UserDto>

    @PostMapping("/api/user")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto>
}