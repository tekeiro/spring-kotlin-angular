package org.keirobm.samples.springangularbackend.authors.web

import org.keirobm.samples.springangularbackend.authors.AuthorService
import org.keirobm.samples.springangularbackend.authors.dto.AuthorDto
import org.keirobm.samples.springangularbackend.books.dto.BookDto
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


/**
 * Api controller for Authors
 */
@RestController
@RequestMapping("/api/authors")
class AuthorApiController
(
        val authorService: AuthorService
)
{

    @RequestMapping("")
    fun allAuthors(): List<AuthorDto>
        = authorService.allAuthors()


    @RequestMapping(
            "create",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun createAuthor(@RequestBody authorInfo: AuthorDto)
        : AuthorDto
        = authorService.createAuthor(authorInfo)


    @RequestMapping(
            "/{id}/books"
    )
    fun getBooksFromAuthor(
            @PathVariable("id") idAuthor: Long
    ): ResponseEntity<List<BookDto>>
        = ResponseEntity.ok(authorService.getBooksFromAuthor(idAuthor))


    @RequestMapping(
            "{id}",
            method = arrayOf(RequestMethod.GET)
    )
    fun getAuthor(@PathVariable("id") idAuthor: Long): ResponseEntity<AuthorDto> {
        val authorRet = authorService.getAuthor(idAuthor)
        return if (authorRet != null) ResponseEntity.ok(authorRet)
            else ResponseEntity.notFound().build()
    }


    @RequestMapping(
            "{id}/edit",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun editAuthor(
            @PathVariable("id") idAuthor: Long,
            @RequestBody authorInfo: AuthorDto
    ): ResponseEntity<AuthorDto>
    {
        val authorRet = authorService.updateAuthor(idAuthor, authorInfo)
        return if (authorRet != null) ResponseEntity.ok(authorRet)
            else ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }


    @RequestMapping(
            "{id}",
            method = arrayOf(RequestMethod.DELETE)
    )
    fun removeAuthor(@PathVariable("id") idAuthor: Long) {
        authorService.removeAuthor(idAuthor)
    }

}