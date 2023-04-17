package com.realtime.auth_microservice.service.impl

import com.realtime.auth_microservice.dao.UserRepository
import com.realtime.auth_microservice.dto.UserDto
import com.realtime.auth_microservice.exception.ResourceAlreadyExistException
import com.realtime.userservice.exception.ResourceNotFoundException
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
    override fun createUser(user: UserDto): UserDto = if (this.userRepository.findByEmail(user.email).isPresent) throw ResourceAlreadyExistException(user.email) else this.mapper.toDomain(this.userRepository.save(this.mapper.toEntity(user)))
    override fun getUser(userId: Long): UserDto = this.mapper.toDomain(this.userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "Id", userId) })
    override fun getAllUsers(): Set<UserDto> = this.userRepository.findAll().stream().map { user -> this.mapper.toDomain(user) }.collect(Collectors.toSet())
    override fun deleteUser(userId: Long): Boolean = this.userRepository.delete(this.userRepository.findById(userId).orElseThrow { ResourceNotFoundException("User", "Id", userId) }).let { true }
}