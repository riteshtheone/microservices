package com.realtime.auth_microservice.entity

import jakarta.persistence.*

@Entity @Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(length = 32, nullable = false)
    var name: String,
    @Column(length = 52, nullable = false, unique = true)
    var email: String,
    @Column(length = 13, nullable = false)
    var phone: String,
    @Column(nullable = false)
    var password: String
)