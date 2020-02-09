package fr.d2factory.libraryapp.book;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A simple representation of a book
 */
public class Book {
    String title;
    String author;
    @JsonProperty("isbn")
    ISBN isbn;

    public Book() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ISBN getIsbn() {
		return isbn;
	}

	public void setIsbn(ISBN isbn) {
		this.isbn = isbn;
	}
    
    
}
