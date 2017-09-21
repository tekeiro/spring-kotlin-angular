package org.keirobm.samples.springangularbackend.users.impl

import org.keirobm.samples.springangularbackend.users.User
import org.keirobm.samples.springangularbackend.users.repo.RoleRepo
import org.keirobm.samples.springangularbackend.users.UserAndRoleService
import org.keirobm.samples.springangularbackend.users.repo.UserRepo
import org.keirobm.samples.springangularbackend.users.dto.UserDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
internal class UserAndRoleServiceImpl
(
        private val userRepo: UserRepo,
        private val roleRepo: RoleRepo,
        private val passwordEncoder: BCryptPasswordEncoder
)
    : UserAndRoleService
{

    override fun findUserByUsername(username: String): UserDto?
        = userRepo.findByUsername(username)?.toDto()


    override fun createUser(userInfo: UserDto): UserDto {
        val user = User(
                null,
                userInfo.username,
                passwordEncoder.encode(userInfo.password),
                userInfo.firstName,
                userInfo.lastName,
                true
        )
        val userRet = userRepo.save(user)
        return userRet.toDto()
    }
}