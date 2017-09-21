package org.keirobm.samples.springangularbackend.books.dto

import com.fasterxml.jackson.annotation.JsonFormat
import org.keirobm.samples.springangularbackend.authors.dto.AuthorDto
import java.time.LocalDateTime


/**
 * Represent a Book into an object that its shared with
 * other layers of business application.
 */
data class BookDto (
        val id: Long,
        val title: String,
        val summary: String,
        @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm", shape = JsonFormat.Shape.STRING)
            val pubDate: LocalDateTime,
        val author: AuthorDto
)
{

    constructor() : this(0L, "", "", LocalDateTime.now(), AuthorDto())

}