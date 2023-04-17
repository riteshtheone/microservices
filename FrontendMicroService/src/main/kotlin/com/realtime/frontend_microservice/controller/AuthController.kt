package com.realtime.frontend_microservice.controller

import com.realtime.frontend_microservice.dto.UserDto
import com.realtime.frontend_microservice.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AuthController(@Autowired private val authService: AuthService) {

    @GetMapping("/")
    fun home() = "views/home"

    @GetMapping("/signin")
    fun signin() = "views/signin"

    @GetMapping("/signup")
    fun signup(): String {
    return "views/signup"
    }

    @PostMapping("/doSignup")
    @ResponseBody
    fun doSignup(@ModelAttribute userDto: UserDto): ResponseEntity<UserDto> {
        println(userDto)
        return this.authService.createUser(userDto)
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    fun getUser(@PathVariable userId: Long) = this.authService.getUser(userId)

    @GetMapping("/user/home")
    fun user_dashboard() = "/user/home"
}