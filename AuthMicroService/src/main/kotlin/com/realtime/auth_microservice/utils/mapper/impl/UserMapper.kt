package com.realtime.auth_microservice.utils.mapper.impl

import com.realtime.auth_microservice.dto.UserDto
import com.realtime.auth_microservice.entity.User
import com.realtime.auth_microservice.utils.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class UserMapper: Mapper<User, UserDto> {
    override fun toEntity(domain: UserDto): User = User(
        domain.id,
        domain.name,
        domain.email,
        domain.phone,
        domain.password
    )

    override fun toDomain(entity: User): UserDto = UserDto(
        entity.id,
        entity.name,
        entity.email,
        entity.phone,
        entity.password
    )
}