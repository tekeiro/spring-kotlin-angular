

import {Component, Input} from "@angular/core";
import {Book} from "../book";


@Component({
  selector: 'book-details',
  templateUrl: './book.details.component.html',
  styles: [
    `.book-detail {
      margin-top: 50px;
    }`
  ]
})
export class BookDetailsComponent {
  @Input() book: Book;
}
