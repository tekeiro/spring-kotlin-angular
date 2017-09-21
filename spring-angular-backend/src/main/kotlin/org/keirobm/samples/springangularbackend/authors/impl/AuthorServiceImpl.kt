package org.keirobm.samples.springangularbackend.authors.impl

import org.keirobm.samples.springangularbackend.authors.Author
import org.keirobm.samples.springangularbackend.authors.AuthorRepo
import org.keirobm.samples.springangularbackend.authors.AuthorService
import org.keirobm.samples.springangularbackend.authors.dto.AuthorDto
import org.keirobm.samples.springangularbackend.books.BookService
import org.keirobm.samples.springangularbackend.books.dto.BookDto
import org.springframework.stereotype.Service

@Service
internal class AuthorServiceImpl
    (
            private val authorRepo: AuthorRepo,
            private val bookService: BookService
    )
    : AuthorService
{
    override fun allAuthors(): List<AuthorDto>
        = authorRepo.findAll().map { it.toDto() }.toList()

    override fun createAuthor(authorInfo: AuthorDto): AuthorDto {
        val authEnt = Author(authorInfo.id, authorInfo.name, authorInfo.surname,
                authorInfo.birthDate)
        val authRet = authorRepo.save(authEnt)
        return authRet.toDto()
    }

    override fun getAuthor(idAuthor: Long): AuthorDto?
        = authorRepo.findOne(idAuthor)?.toDto()

    override fun updateAuthor(idAuthor: Long, authorInfo: AuthorDto): AuthorDto? {
        val authEnt = authorRepo.findOne(idAuthor)
        return authEnt?.let { authEnt ->
            authEnt.fromUpdateDto(authorInfo)
            val authRet = authorRepo.save(authEnt)
            return authRet.toDto()
        }
    }

    override fun removeAuthor(idAuthor: Long) {
        val author = authorRepo.findOne(idAuthor)
        author?.let { author ->
            author.books.forEach { b ->
                bookService.removeBook(b.id!!)
            }
            authorRepo.delete(idAuthor)
        }

    }

    override fun getBooksFromAuthor(idAuthor: Long): List<BookDto> {
        val author = authorRepo.findOne(idAuthor)
        return author?.books?.map { b -> b.toDto() }?.toList()
                ?: listOf()
    }

}