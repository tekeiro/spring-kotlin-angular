

import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

import 'rxjs/add/operator/toPromise';

import {Book} from "./book";


/**
 * Service that operates with Book
 * entities.
 */
@Injectable()
export class BookService {
  private ALL_BOOKS_URL = "/api/books";
  private CREATE_BOOKS_URL = "/api/books/create";
  private GET_BOOK_URL = "/api/books";
  private EDIT_BOOK_URL = "/api/books/{id}/edit";
  private REMOVE_BOOK_URL = "/api/books/{id}";


  constructor(
    private http: Http
  )
  {  }


  allBooks(): Promise<Book[]> {
    return this.http.get(this.ALL_BOOKS_URL)
      .toPromise()
      .then( response => {
        let list = <Book[]>[];
        response.json().forEach( (item, ) => {
          list.push(Book.fromJson(item));
        });
        return list;
      })
      .catch(this.handleError);
  }

  createBook(bookInfo: Book): Promise<boolean> {
    return this.http.post(this.CREATE_BOOKS_URL, bookInfo)
      .toPromise()
      .then(response => {
        console.log("response ", response.json());
        return true;
      })
      .catch(this.handleError)
    ;
  }

  getBook(id: number): Promise<Book> {
    let url = `${this.GET_BOOK_URL}/${id}`;

    return this.http.get(url)
      .toPromise()
      .then( response => {
        return Book.fromJson(response.json());
      })
      .catch(this.handleError)
    ;
  }

  editBook(id:number, book: Book): Promise<Book> {
    let url = this.EDIT_BOOK_URL.replace("{id}", id.toString());

    return this.http.post(url, book)
      .toPromise()
      .then( response => {
           return Book.fromJson(response.json());
      })
      .catch(this.handleError)
    ;
  }

  removeBook(id: number): Promise<boolean> {
    let url = this.REMOVE_BOOK_URL.replace("{id}", id.toString());

    return this.http.delete(url)
      .toPromise()
      .then( response => {
        return true;
      })
      .catch(this.handleError)
    ;
  }


  private handleError(error: any): Promise<any> {
    console.error("An error occurred: BookService ", error);
    return Promise.reject(error.message || error);
  }
}
