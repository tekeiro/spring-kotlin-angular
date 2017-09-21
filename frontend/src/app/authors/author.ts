

/**
 * Author model
 */
export class Author {

  constructor(
    public id: number,
    public name: string,
    public surname: string,
    public birthDate: Date
  )
  {  }

  public get fullName() {
    return `${this.name} ${this.surname}`;
  }

  /**
   * To build an Author object
   * from a response object for example
   * a response from a AJAX call.
   * @param {Author} body Object with
   * same properties than Author.
   * @returns {Author} Newly created author.
   */
  public static fromJson(body: Author): Author {
    return new Author(
      body.id,
      body.name,
      body.surname,
      body.birthDate
    );
  }

}
