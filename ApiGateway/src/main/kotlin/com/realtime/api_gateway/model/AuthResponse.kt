package com.realtime.api_gateway.model

data class AuthResponse(
    var userId: String,
    var accessToken: String,
    var refreshToken: String,
    var expireAt: Long,
    var authorities: Collection<String>
)
