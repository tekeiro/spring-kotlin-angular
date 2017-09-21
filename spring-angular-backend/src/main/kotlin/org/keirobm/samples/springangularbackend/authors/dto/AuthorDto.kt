package org.keirobm.samples.springangularbackend.authors.dto

import java.time.LocalDate


/**
 * Represent a Author into an object that its shared with
 * with other layers of business application
 */
data class AuthorDto (
        val id: Long,
        val name: String,
        val surname: String,
        val birthDate: LocalDate
)
{

    constructor() : this(0L, "", "", LocalDate.now())

}