package com.realtime.auth_microservice.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDto(
    val id: Long,
    @field:NotBlank @field:Size(min = 3, max = 32)
    var name: String?,
    @field:NotBlank @field:Email
    var email: String?,
    @field:NotBlank @field:Size(min = 10, max = 10)
    var phone: String?,
    @field:NotBlank @field:Size(min = 4, max = 32)
    var password: String?
)