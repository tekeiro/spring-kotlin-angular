package org.keirobm.samples.springangularbackend.authors

import org.keirobm.samples.springangularbackend.authors.dto.AuthorDto
import org.keirobm.samples.springangularbackend.books.dto.BookDto


/**
 * DAO Service that it has all operations that it can
 * be done with Authors
 */
interface AuthorService {


    /**
     * Returns all authors in a list.
     */
    fun allAuthors(): List<AuthorDto>

    /**
     * Create a new author with information
     * passed.
     */
    fun createAuthor(authorInfo: AuthorDto): AuthorDto


    /**
     * Obtain an Author given its ID.
     */
    fun getAuthor(idAuthor: Long): AuthorDto?


    /**
     * Updates information about an author
     * whose ID is idAuthor given and new information
     * is in authorInfo object passed.
     */
    fun updateAuthor(idAuthor: Long, authorInfo: AuthorDto): AuthorDto?

    /**
     * Obtain all books from an author ID given
     * in a list.
     */
    fun getBooksFromAuthor(idAuthor: Long): List<BookDto>


    /**
     * Removes an author from repository.
     */
    fun removeAuthor(idAuthor: Long)
}