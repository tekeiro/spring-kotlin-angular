import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";

import { AppComponent } from './app.component';
import {NavbarComponent} from "./ui/navbar/navbar.component";
import {AppRoutingModule} from "./app-routing.module";
import {BookService} from "./books/book.service";
import {BookListComponent} from "./books/list/book.list.component";
import {BookCreateComponent} from "./books/create/book.create.component";
import {BookEditComponent} from "./books/edit/book.edit.component";
import {AuthorListComponent} from "./authors/list/author.list.component";
import {AuthorService} from "./authors/author.service";
import {AuthorCreateComponent} from "./authors/create/author.create.component";
import {BookDetailsComponent} from "./books/details/book.details.component";
import {AuthorDetailsComponent} from "./authors/details/author.details.component";
import {AuthorEditComponent} from "./authors/edit/author.edit.component";




@NgModule({
  declarations: [
    AppComponent,

    NavbarComponent,

    BookListComponent,
    BookCreateComponent,
    BookEditComponent,
    BookDetailsComponent,

    AuthorListComponent,
    AuthorCreateComponent,
    AuthorDetailsComponent,
    AuthorEditComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule
  ],
  providers: [
    BookService,
    AuthorService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
