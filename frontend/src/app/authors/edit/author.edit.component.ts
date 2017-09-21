

import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {Location} from '@angular/common';

import 'rxjs/add/operator/switchMap';

import {Author} from "../author";
import {AuthorService} from "../author.service";


@Component({
  selector: 'author-edit',
  templateUrl: './author.edit.component.html'
})
export class AuthorEditComponent implements OnInit{
  author: Author;

  constructor(
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private location: Location
  )
  { }

  ngOnInit() {
    this.route.paramMap
      .switchMap( (params: ParamMap) =>
        this.authorService.getAuthor(+params.get("id"))
      )
      .subscribe( author => {
        this.author = author;
      })
    ;
  }

  onSubmit() {
    console.log("Author to post: ", this.author);
    this.authorService.editAuthor(this.author.id, this.author)
      .then( author => {
        this.author = author;
        alert("Edicion correcta de autor");
      })
    ;
  }
}
