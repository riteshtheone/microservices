package com.realtime.auth_microservice.exception.handler

import com.realtime.auth_microservice.exception.ResourceAlreadyExistException
import com.realtime.userservice.exception.ResourceNotFoundException
import com.realtime.auth_microservice.payload.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): Map<String, String?>? {
        val errors: MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.forEach { error: ObjectError ->
            errors[(error as FieldError).field] = error.getDefaultMessage()
        }
        return errors
    }

    @ExceptionHandler(ResourceNotFoundException::class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException): ApiResponse = ApiResponse(exception.message.toString(), false)

    @ExceptionHandler(ResourceAlreadyExistException::class) @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleResourceAlreadyExistException(exception: ResourceAlreadyExistException): ApiResponse = ApiResponse(exception.message.toString(), false)
}