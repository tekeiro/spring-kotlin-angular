


import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

import 'rxjs/add/operator/toPromise';
import {Author} from "./author";
import {Book} from "../books/book";



@Injectable()
export class AuthorService {
  private ALL_AUTHORS_URL = "/api/authors";
  private CREATE_AUTHOR_URL = "/api/authors/create";
  private FETCH_BOOKS_OF_AUTHOR_URL = `/api/authors/{id}/books`;
  private GET_AUTHOR_URL = `/api/authors/{id}`;
  private EDIT_AUTHOR_URL = `/api/authors/{id}/edit`;
  private REMOVE_AUTHOR_URL = `/api/authors/{id}`;


  // Dependency injection
  constructor(
    private http: Http
  )
  {  }


  allAuthors(): Promise<Author[]> {
    return this.http.get(this.ALL_AUTHORS_URL)
      .toPromise()
      .then( response => {
        let list = <Author[]>[];
        response.json().forEach( (item, ) => {
          list.push(Author.fromJson(item));
        });
        return list;
      })
      .catch(this.handleError)
    ;
  }


  createAuthor(authorInfo: Author): Promise<boolean> {
    return this.http.post(this.CREATE_AUTHOR_URL, authorInfo)
      .toPromise()
      .then( response => {
        console.log("response ", response.json());
        return true;
      })
      .catch(this.handleError)
    ;
  }


  fetchBooksFromAuthor(idAuthor: number): Promise<Book[]> {
    let url = this.FETCH_BOOKS_OF_AUTHOR_URL.replace("{id}", idAuthor.toString());
    return this.http.get(url)
      .toPromise()
      .then( response => {
        let list = <Book[]>[];
        response.json().forEach( (item, ) => {
          list.push(Book.fromJson(item));
        });
        return list;
      })
      .catch(this.handleError)
    ;
  }

  getAuthor(id: number): Promise<Author> {
    let url = this.GET_AUTHOR_URL.replace("{id}", id.toString());

    return this.http.get(url)
      .toPromise()
      .then( response => {
        return Author.fromJson(response.json());
      })
      .catch(this.handleError)
    ;
  }

  editAuthor(id: number, authorInfo: Author): Promise<Author> {
    let url = this.EDIT_AUTHOR_URL.replace("{id}", id.toString());

    return this.http.post(url, authorInfo)
      .toPromise()
      .then( response => {
        return Author.fromJson(response.json());
      })
      .catch(this.handleError)
      ;
  }

  removeAuthor(id: number): Promise<boolean> {
    let url = this.REMOVE_AUTHOR_URL.replace("{id}", id.toString());

    return this.http.delete(url)
      .toPromise()
      .then( response =>  {
        return true;
      })
      .catch(this.handleError)
    ;
  }


  private handleError(error: any): Promise<any> {
    console.error("An error occurred: AuthorService ", error);
    return Promise.reject(error.message || error);
  }
}
