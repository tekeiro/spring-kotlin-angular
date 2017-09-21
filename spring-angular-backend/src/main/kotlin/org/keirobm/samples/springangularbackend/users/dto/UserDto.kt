package org.keirobm.samples.springangularbackend.users.dto

data class UserDto (
        val id: Long,
        val username: String,
        val password: String,
        val firstName: String,
        val lastName: String,
        val active: Boolean,
        val roles: List<RoleDto>
) {

    constructor() : this(0L, "", "", "", "", true, listOf())

}