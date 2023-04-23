package com.realtime.api_gateway.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {

    @Bean
    fun securityWebFilterChain(serverHttpSecurity: ServerHttpSecurity): SecurityWebFilterChain = let {
        serverHttpSecurity.authorizeExchange().anyExchange().authenticated().and().oauth2Client().and().oauth2ResourceServer().jwt()
        serverHttpSecurity.build()
    }
}