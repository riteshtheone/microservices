package com.realtime.frontend_microservice.controller

import com.realtime.frontend_microservice.dto.UserDto
import com.realtime.frontend_microservice.service.AuthService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.lang.Exception

@Controller
@RequestMapping("/auth")
class AuthController(@Autowired private val authService: AuthService) {

    private val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)
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

    var retryCount = 0
    @GetMapping("/users")
//    @CircuitBreaker(name = "apiAuthBreaker", fallbackMethod = "apiAuthFallBack")
//    @Retry(name = "apiAuthService", fallbackMethod = "apiAuthFallBack")
    @RateLimiter(name = "apiAuthRateLimiter", fallbackMethod = "apiAuthFallBack")
    @ResponseBody
    fun getAllUsers(): ResponseEntity<Set<UserDto>> = let {
        logger.info("get all users: apiAuthController:8082")
        logger.info("Retry count: {}", ++retryCount)
        if (retryCount == 3) retryCount = 0
        this.authService.getAllUsers()
    }

    fun apiAuthFallBack(exception: Exception): ResponseEntity<Set<UserDto>> = ResponseEntity.ok(setOf( UserDto().apply {
            logger.info("service is down !!! ${exception.localizedMessage}")
            this.name = "Fall Back"
            this.email = "fallback@email.com"
            this.phone = "1234567898"
            this.password = "fallback"
    }))
}