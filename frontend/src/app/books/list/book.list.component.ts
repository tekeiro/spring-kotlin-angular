

import {Component, OnInit} from "@angular/core";
import {BookService} from "../book.service";
import {Book} from "../book";


@Component({
  selector: 'book-list',
  templateUrl: "./book.list.component.html"
})
export class BookListComponent implements OnInit {
  books: Book[];
  selectedBook: Book;

  constructor(
    private bookService: BookService
  )
  { }


  ngOnInit() {
    this.loadAllBooks();
  }

  private loadAllBooks() {
    this.bookService.allBooks()
      .then(books => this.books = books)
    ;
  }

  selectBook(book: Book) {
    this.selectedBook = book;
  }

  removeBook(book: Book) {
    this.bookService.removeBook(book.id)
      .then( result => {
        alert("El borrado ha ido: " + result);
        this.loadAllBooks();
      })
    ;
  }

}
