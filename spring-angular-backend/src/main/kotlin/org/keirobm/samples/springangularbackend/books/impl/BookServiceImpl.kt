package org.keirobm.samples.springangularbackend.books.impl

import org.keirobm.samples.springangularbackend.authors.AuthorRepo
import org.keirobm.samples.springangularbackend.authors.AuthorService
import org.keirobm.samples.springangularbackend.books.Book
import org.keirobm.samples.springangularbackend.books.BookRepo
import org.keirobm.samples.springangularbackend.books.BookService
import org.keirobm.samples.springangularbackend.books.dto.BookDto
import org.springframework.stereotype.Service


@Service
internal class BookServiceImpl
    (
            private val bookRepo: BookRepo,
            private val authorRepo: AuthorRepo
    )
    : BookService
{

    override fun allBooks(): List<BookDto>
            = bookRepo.findAll().map { book -> book.toDto() }.toList()

    override fun createBook(bookInfo: BookDto): BookDto {
        val author = authorRepo.findOne(bookInfo.author.id)!!
        val book = Book(null, bookInfo.title, bookInfo.summary, bookInfo.pubDate,
                author)
        val bookRet = bookRepo.save(book)
        return bookRet.toDto()
    }


    override fun getBook(idBook: Long): BookDto?
        = bookRepo.findOne(idBook)?.toDto()


    override fun updateBook(idBook: Long, bookInfo: BookDto): BookDto? {
        val bookEnt = bookRepo.findOne(idBook)
        return bookEnt?.let { bookEnt ->
            bookEnt.fromUpdateDto(bookInfo)
            changeAuthorFromBook(bookEnt, bookInfo.author.id)
            //val bookRet = bookRepo.save(bookEnt)
            //bookRet.toDto()
        }
    }

    private fun changeAuthorFromBook(book: Book, idNewAuthor: Long): BookDto {
        var bookRet: Book? = null
        if (book.author.id != idNewAuthor) {
            val newAuthor = authorRepo.findOne(idNewAuthor)
            if (newAuthor != null) {
                book.author.removeBook(book)
                //bookRepo.save(book)
                authorRepo.save(book.author)
                book.author = newAuthor
                newAuthor.addBook(book)
                bookRet = bookRepo.save(book)
                authorRepo.save(newAuthor)
            }
        }
        return if (bookRet != null) bookRet.toDto()
            else bookRepo.save(book).toDto()
    }


    override fun removeBook(idBook: Long) {
        val book = bookRepo.findOne(idBook)
        book?.let { book ->
            val author = book.author
            author.removeBook(book)
            bookRepo.delete(book)
            authorRepo.save(author)
        }
    }

}