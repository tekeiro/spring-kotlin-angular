

import {Component} from "@angular/core";
import {AuthorService} from "../author.service";
import {Author} from "../author";

@Component({
  selector: 'author-create',
  templateUrl: './author.create.component.html'
})
export class AuthorCreateComponent {
  author: Author;


  constructor(
    private authorService: AuthorService
  )
  {
    // Initialize Author form with empty fields
    this.author = new Author(0, "", "", new Date());
  }

  onSubmit() {
    console.log("Author creating: ", this.author);
    this.authorService.createAuthor(this.author)
      .then( result => {
           alert(`Resultado de crear autor ${result}`);
      })
    ;
  }

}
