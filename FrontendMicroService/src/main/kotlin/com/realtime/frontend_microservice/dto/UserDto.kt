package com.realtime.frontend_microservice.dto

class UserDto{
    val id: Long = 0
    var name: String = ""
    var email: String = ""
    var phone: String = ""
    var password: String = ""
    override fun toString(): String = "UserDto(id=$id, name='$name', email='$email', phone='$phone', password='$password')"
}