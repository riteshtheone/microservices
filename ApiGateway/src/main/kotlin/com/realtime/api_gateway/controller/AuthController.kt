package com.realtime.api_gateway.controller

import com.realtime.api_gateway.model.AuthResponse

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.stream.Collectors

@RestController @RequestMapping("/gateway/auth")
class AuthController {

    var name: String? = null

    val logger: Logger = LoggerFactory.getLogger(AuthController::class.java)
    @GetMapping("/login")
    fun login(
        @RegisteredOAuth2AuthorizedClient("okta") client: OAuth2AuthorizedClient,
        @AuthenticationPrincipal user: OidcUser,
        model: Model
        ): ResponseEntity<AuthResponse> = let {
        logger.info("user email id : {} ", user.email)
        ResponseEntity.ok (AuthResponse (
            userId = user.email,
            accessToken = client.accessToken.tokenValue,
            refreshToken = client.refreshToken!!.tokenValue,
            expireAt = client.accessToken.expiresAt!!.epochSecond,
            authorities = user.authorities.stream().map { grantedAuthority -> grantedAuthority.authority }.collect(Collectors.toList())
        ))
    }
}