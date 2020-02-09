package fr.d2factory.libraryapp.book;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

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
	
	@Override
	public boolean equals(Object obj) {
		if(obj==this){
			return true;
		}
		if(!(obj instanceof Book)){
			return false;
		}
		
		Book bookObj= (Book) obj;
		EqualsBuilder equalsBuilder=new EqualsBuilder();
		equalsBuilder.append(this.author, bookObj.getAuthor()).append(this.isbn,bookObj.isbn)
		.append(this.title, bookObj.title);
		return equalsBuilder.isEquals();
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(author,title,isbn);
	}
    
    
}
