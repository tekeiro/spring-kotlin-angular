package org.keirobm.samples.springangularbackend.books.web

import org.keirobm.samples.springangularbackend.books.BookService
import org.keirobm.samples.springangularbackend.books.dto.BookDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * Api controller for Books
 */
@RestController
@RequestMapping("/api/books")
class BookApiController
    (val bookService: BookService)
{


    @RequestMapping("")
    fun allBooks(): List<BookDto>
        = bookService.allBooks()

    @RequestMapping(
            "create",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun createBook(@RequestBody bookInfo: BookDto): BookDto {
        return bookService.createBook(bookInfo)
    }


    @RequestMapping("{id}",
            method = arrayOf(RequestMethod.GET)
    )
    fun getBook(@PathVariable("id") idBook: Long): ResponseEntity<BookDto> {
        val bookRet = bookService.getBook(idBook)
        return if (bookRet != null) ResponseEntity.ok(bookRet)
            else ResponseEntity.notFound().build()
    }

    @RequestMapping("{id}",
            method = arrayOf(RequestMethod.DELETE)
    )
    fun removeBook(@PathVariable("id") idBook: Long)
    {
        bookService.removeBook(idBook)
    }


    @RequestMapping("{id}/edit", method = arrayOf(RequestMethod.POST))
    fun editBook(
            @PathVariable("id") idBook: Long,
            @RequestBody bookInfo: BookDto
    ) : ResponseEntity<BookDto> {
        val bookRet = bookService.updateBook(idBook, bookInfo)
        return if (bookRet != null) ResponseEntity.ok(bookRet)
            else ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

}