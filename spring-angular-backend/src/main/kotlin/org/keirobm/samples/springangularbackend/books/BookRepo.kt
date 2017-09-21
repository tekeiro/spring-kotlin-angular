package org.keirobm.samples.springangularbackend.books

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Spring JPA Data repository that do databases operations
 * with {@link Book}
 */
@Repository
internal interface BookRepo : JpaRepository<Book, Long>