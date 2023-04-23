package com.realtime.frontend_microservice.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class AppConfig {

//    @Bean
//    fun logger(): Logger = LoggerFactory.getLogger(FrontendMicroServiceApplication::class.java)

    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate = RestTemplate()
}