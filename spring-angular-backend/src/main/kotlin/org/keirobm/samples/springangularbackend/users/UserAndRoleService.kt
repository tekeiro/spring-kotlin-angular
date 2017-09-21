package org.keirobm.samples.springangularbackend.users

import org.keirobm.samples.springangularbackend.users.dto.UserDto


/**
 * Service to manage Users and Roles
 */
interface UserAndRoleService {

    /**
     * Find an User given a Username.
     * Returns null if user not exists with username.
     */
    fun findUserByUsername(username: String): UserDto?

    /**
     * Create a new user with information given
     * in userInfo parameter
     */
    fun createUser(userInfo: UserDto): UserDto

}