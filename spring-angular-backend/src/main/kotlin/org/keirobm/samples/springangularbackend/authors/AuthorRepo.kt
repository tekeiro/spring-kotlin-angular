package org.keirobm.samples.springangularbackend.authors

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface AuthorRepo : JpaRepository<Author, Long>{
}