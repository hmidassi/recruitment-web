package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.List;

public interface IBookRepository {
	public Book findBook(long isbnCode);
	
	public void saveBookBorrow(Book book, LocalDate borrowedAt);
	
	public void makeBookAvailable(Book book);
	
	public LocalDate findBorrowedBookDate(Book book);
	
	public void addBooks(List<Book> books);
	
	public void makeBookUnavailable(long isbnCode);
}
