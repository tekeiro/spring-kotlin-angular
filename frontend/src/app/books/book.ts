/**
 * Represent information needed to
 * view a Book
 */


import * as moment from "moment";
import {Author} from "../authors/author";

export class Book {

  constructor(
    public id: number,
    public title: string,
    public summary: string,
    public pubDate: Date,
    public author: Author
  )
  {  }


  /**
   * To build a Book object from a
   * response data from an AJAX call
   * @param body Object with same properties that Book.
   * @returns {Book} Newly created book.
   */
  public static fromJson(body: Book): Book {
    let pubDate: Date = body.pubDate;
    if (typeof(body.pubDate) == "string") {
      pubDate = moment(body.pubDate, "YYYY-MM-DD'T'HH:mm:ss").toDate()
    }

    return new Book (
      body.id,
      body.title,
      body.summary,
      pubDate,
      Author.fromJson(body.author)
    );
  }

}
