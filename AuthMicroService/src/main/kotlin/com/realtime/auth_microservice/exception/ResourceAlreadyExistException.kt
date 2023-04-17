package com.realtime.auth_microservice.exception

class ResourceAlreadyExistException(resourceName: String): RuntimeException(String.format("%s is already exist", resourceName))