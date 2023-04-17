package com.realtime.frontend_microservice.exception.handler

import feign.FeignException

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(FeignException::class) @ResponseStatus(HttpStatus.BAD_REQUEST) @ResponseBody
    fun handleFeignClientException(exception: FeignException): ResponseEntity<String> {
        println(exception.status())
        println(exception.message)
        println(exception.localizedMessage)
        println(exception.cause)
        println(exception.contentUTF8())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.contentUTF8().toString())
    }
}