package org.keirobm.samples.springangularbackend.books

import org.keirobm.samples.springangularbackend.books.dto.BookDto


/**
 * DAO Service that it has all operations that it can
 * be done with books.
 */
interface BookService {

    /**
     * Returns all books.
     * @return A list with all books.
     */
    fun allBooks(): List<BookDto>


    /**
     * Create a new book with information
     * of BookDto.
     */
    fun createBook(bookInfo: BookDto): BookDto


    /**
     * Obtain a Book given its ID.
     * Returns null if book not exists.
     */
    fun getBook(idBook: Long): BookDto?

    /**
     * Updates information about a Book.
     * Returns updated book or null if not exists.
     */
    fun updateBook(idBook: Long, bookInfo: BookDto): BookDto?

    /**
     * Remove definitely a book
     */
    fun removeBook(idBook: Long)
}