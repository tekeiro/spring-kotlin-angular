


import {Component, OnInit} from "@angular/core";
import {AuthorService} from "../author.service";
import {Author} from "../author";

@Component({
  selector: 'author-list',
  templateUrl: './author.list.component.html'
})
export class AuthorListComponent implements OnInit {
  authors: Author[];
  selectedAuthor: Author;

  constructor(
    private authorService: AuthorService
  )
  {  }


  ngOnInit() {
    this.loadAuthors();
  }

  private loadAuthors() {
    this.authorService.allAuthors()
      .then(authors => {
        this.authors = authors;
      });
  }

  selectAuthor(author: Author) {
    this.selectedAuthor = author;
  }

  removeAuthor(author: Author) {
    this.authorService.removeAuthor(author.id)
      .then( result => {
           this.loadAuthors();
      })
    ;
  }

}
