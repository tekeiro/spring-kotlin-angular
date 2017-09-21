
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Location} from '@angular/common';

import 'rxjs/add/operator/switchMap';
import * as moment from "moment";

import {Book} from "../book";
import {BookService} from "../book.service";
import {Author} from "../../authors/author";
import {AuthorService} from "../../authors/author.service";


@Component({
  selector: 'book-edit',
  templateUrl: './book.edit.component.html'
})
export class BookEditComponent implements OnInit{

  book: Book;
  authors: Author[];

  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private location: Location
  )
  {  }


  ngOnInit() {
    this.route.paramMap
      .switchMap( (params: ParamMap) =>
        this.bookService.getBook(+params.get("id"))
      )
      .subscribe(
        book => {
          this.book = book;
          console.log("Book: ", this.book);
        }
      )
    ;

    this.authorService.allAuthors()
      .then( authors =>  {
        this.authors = authors;
      })
    ;
  }

  goBack() {
    this.location.back();
  }


  parseDate(text: string): Date {
    return moment(text, "YYYY-MM-DD'T'HH:mm").toDate();
  }

  onSubmit() {
    console.log("Book ", this.book);
    this.bookService.editBook(this.book.id, this.book)
      .then( book => {
        this.book = book;
        alert("Edicion correcta del libro");
      })
    ;
  }

}
