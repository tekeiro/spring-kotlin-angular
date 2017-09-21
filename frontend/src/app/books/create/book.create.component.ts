

import {BookService} from "../book.service";
import {Component, OnInit} from "@angular/core";
import {Book} from "../book";
import {AuthorService} from "../../authors/author.service";
import {Author} from "../../authors/author";


@Component({
  selector: 'book-create',
  templateUrl: './book.create.component.html'
})
export class BookCreateComponent implements OnInit {
  book: Book;
  authors: Author[];

  constructor(
    private bookService: BookService,
    private authorService: AuthorService
  )
  {
    this.book = new Book(0, "", "", new Date(),
      new Author(0, "", "", new Date()));
  }

  ngOnInit() {
    this.authorService.allAuthors()
      .then( authors => {
        this.authors = authors;
      })
    ;
  }

  onSubmit() {
    console.log("Book: ", this.book);
    this.bookService.createBook(this.book)
      .then(result => {
        alert("Creado correctamente el libro");
      })
    ;
  }

}
