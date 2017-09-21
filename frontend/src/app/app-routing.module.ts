
import {Route, RouterModule} from "@angular/router";
import {NgModule, Type} from "@angular/core";


import {BookListComponent} from "./books/list/book.list.component";
import {BookCreateComponent} from "./books/create/book.create.component";
import {BookEditComponent} from "./books/edit/book.edit.component";
import {AuthorListComponent} from "./authors/list/author.list.component";
import {AuthorCreateComponent} from "./authors/create/author.create.component";
import {AuthorEditComponent} from "./authors/edit/author.edit.component";

export function simpleRoute(path: string, component: Type<any>): Route {
  return {path: path, component: component}
}

export const routes: Route[] = [
  // Books
  simpleRoute('books', BookListComponent),
  simpleRoute('books/create', BookCreateComponent),
  simpleRoute('books/:id', BookEditComponent),

  // Authors
  simpleRoute('authors', AuthorListComponent),
  simpleRoute('authors/create', AuthorCreateComponent),
  simpleRoute('authors/:id', AuthorEditComponent)
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule
{ }
