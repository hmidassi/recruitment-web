package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
	private Map<ISBN, Book> availableBooks = new HashMap<>();
	private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

	public void addBooks(List<Book> books) {
		Map<ISBN, Book> booksToMap = books.stream().collect(Collectors.toMap(Book::getIsbn, Function.identity()));
		availableBooks.putAll(booksToMap);
	}

	// finding available books only
	public Book findBook(long isbnCode) {
		ISBN isbn = new ISBN(isbnCode);
		Book book = availableBooks.get(isbn);

		if (book != null) {
			System.out.println("book title: " + book.getTitle());
		} else {
			System.out.println("No book found for ISBN :" + String.valueOf(isbnCode));
		}

		return book;
	}

	public void saveBookBorrow(Book book, LocalDate borrowedAt) {
		borrowedBooks.put(book, borrowedAt);
	}
	
	public void makeBookUnavailable(long isbnCode){
		ISBN isbn = new ISBN(isbnCode);
		availableBooks.remove(isbn);
	}

	public LocalDate findBorrowedBookDate(Book book) {
		return borrowedBooks.get(book);
	}
}
