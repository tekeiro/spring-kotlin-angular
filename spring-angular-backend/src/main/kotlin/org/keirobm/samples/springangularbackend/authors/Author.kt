package org.keirobm.samples.springangularbackend.authors

import org.keirobm.samples.springangularbackend.authors.dto.AuthorDto
import org.keirobm.samples.springangularbackend.books.Book
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(name="authors")
internal data class Author (
        @Id @GeneratedValue var id: Long?,
        var name: String,
        var surname: String,
        var birthDate: LocalDate
) {

    constructor() : this(null, "", "", LocalDate.now())


    @OneToMany(mappedBy = "author")
    private var mBooks: MutableSet<Book> = hashSetOf()


    val books: Set<Book>
        get() = mBooks.toSet()


    internal fun addBook(book: Book) {
        book.author = this
        this.mBooks.add(book)
    }

    internal fun removeBook(book: Book) {
        if (book in this.mBooks) {
            this.mBooks.remove(book)
        }
    }


    fun toDto(): AuthorDto
        = AuthorDto(
            id ?: 0L,
            name, surname,
            birthDate
        )

    fun fromUpdateDto(authorInfo: AuthorDto) {
        name = authorInfo.name
        surname = authorInfo.surname
        birthDate = authorInfo.birthDate
    }

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other == null)
            return false
        if (!Author::class.isInstance(other))
            return false
        val a = other as Author
        if (this.id == null || a.id == null)
            return false
        return this.id == a.id
    }

    override fun hashCode(): Int {
        val result = 17
        val c = (id?.xor( (id?.ushr(32) ?: 17) ) ?: 17).toInt()
        return result * 37 + c
    }

}