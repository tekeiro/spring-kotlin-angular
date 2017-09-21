package org.keirobm.samples.springangularbackend.users.repo

import org.keirobm.samples.springangularbackend.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
internal interface UserRepo : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}