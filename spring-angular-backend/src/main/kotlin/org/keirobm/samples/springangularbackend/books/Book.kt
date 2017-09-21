package org.keirobm.samples.springangularbackend.books

import org.keirobm.samples.springangularbackend.authors.Author
import org.keirobm.samples.springangularbackend.books.dto.BookDto
import java.time.LocalDateTime
import javax.persistence.*


/**
 * Book entity
 */
@Entity
@Table(name="books")
internal data class Book (
        @Id @GeneratedValue var id: Long?,
        var title: String,
        var summary: String,
        var pubDate: LocalDateTime,
        @ManyToOne @JoinColumn(name="author_id")
            var author: Author
)
{

    // Default constructor
    constructor() : this(null, "", "", LocalDateTime.now(), Author())

    /**
     * Return a DTO representation of this
     * DB entity.
     */
    fun toDto(): BookDto
        = BookDto(id ?: -1L, title, summary, pubDate,
            author.toDto())

    /**
     * Update this entity with
     * information of a BookDto object.
     */
    fun fromUpdateDto(bookInfo: BookDto) {
        this.title = bookInfo.title
        this.summary = bookInfo.summary
        this.pubDate = bookInfo.pubDate
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (!Book::class.isInstance(other))
            return false
        val b = other as Book
        if (this.id == null || b.id == null)
            return false
        return this.id == b.id
    }


    override fun hashCode(): Int {
        val result = 13
        val c = (id?.xor( (id?.ushr(32) ?: 13) ) ?: 13).toInt()
        return result * 37 + c
    }


}