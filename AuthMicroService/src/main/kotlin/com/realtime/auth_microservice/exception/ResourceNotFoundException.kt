package com.realtime.userservice.exception

class ResourceNotFoundException(resourceName: String, fieldName: String, fieldValue: Long) :
    RuntimeException(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue))
