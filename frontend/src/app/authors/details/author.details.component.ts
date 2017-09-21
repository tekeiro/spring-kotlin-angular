

import {Component, Input, OnChanges} from "@angular/core";
import {Author} from "../author";
import {Book} from "../../books/book";
import {AuthorService} from "../author.service";


@Component({
  selector: 'author-detail',
  templateUrl: './author.details.component.html'
})
export class AuthorDetailsComponent implements OnChanges{
  @Input() author: Author;
  books: Book[];

  constructor(
    private authorService: AuthorService
  )
  { }

  ngOnChanges() {
    if (this.author) {
      this.authorService.fetchBooksFromAuthor(this.author.id)
        .then(books => {
          this.books = books;
        })
      ;
    }
  }
}
