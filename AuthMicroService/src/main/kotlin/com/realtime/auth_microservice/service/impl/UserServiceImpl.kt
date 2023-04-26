package com.realtime.auth_microservice.service.impl

import com.realtime.auth_microservice.dao.UserRepository
import com.realtime.auth_microservice.dto.UserDto
import com.realtime.auth_microservice.exception.ResourceAlreadyExistException
import com.realtime.auth_microservice.exception.ResourceNotFoundException
import com.realtime.auth_microservice.service.UserService
import com.realtime.auth_microservice.utils.mapper.impl.UserMapper

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserServiceImpl(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val mapper: UserMapper
    ) : UserService {
    override fun createUser(userDto: UserDto): UserDto = if (this.userRepository.findByEmail(userDto.email!!).isPresent) throw ResourceAlreadyExistException(userDto.email!!) else this.mapper.toDomain(this.userRepository.save(this.mapper.toEntity(userDto)))
    override fun updateUser(userDto: UserDto, userId: Long): UserDto = this.mapper.toDomain(this.userRepository.save(this.userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "Id", userId) }.apply {
        name = userDto.name!!
        email = userDto.email!!
        phone = userDto.phone!!
        password = userDto.password!!
    }))

    override fun getUser(userId: Long): UserDto = this.mapper.toDomain(this.userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "Id", userId) })
    override fun getAllUsers(): Set<UserDto> = this.userRepository.findAll().stream().map { user -> this.mapper.toDomain(user) }.collect(Collectors.toSet())
    override fun deleteUser(userId: Long): Boolean = this.userRepository.delete(this.userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "Id", userId) }).let { true }
}