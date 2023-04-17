package com.realtime.auth_microservice.utils.mapper

interface Mapper<E, D> {
    fun toEntity(domain: D): E
    fun toDomain(entity: E): D
}