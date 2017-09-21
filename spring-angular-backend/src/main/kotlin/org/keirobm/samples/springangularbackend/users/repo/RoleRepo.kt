package org.keirobm.samples.springangularbackend.users.repo

import org.keirobm.samples.springangularbackend.users.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface RoleRepo : JpaRepository<Role, Long> {

    fun findByRole(role: String): Role?

}