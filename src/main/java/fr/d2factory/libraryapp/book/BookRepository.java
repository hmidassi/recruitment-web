package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository implements IBookRepository {
	private Map<ISBN, Book> availableBooks = new HashMap<>();
	private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

	
	@Override
	public void addBooks(List<Book> books) {
		Map<ISBN, Book> booksToMap = books.stream().collect(Collectors.toMap(Book::getIsbn, Function.identity()));
		availableBooks.putAll(booksToMap);
	}

	// finding available books only
	@Override
	public Book findBook(long isbnCode) {
		ISBN isbn = new ISBN(isbnCode);
		Book book = availableBooks.get(isbn);
		return book;
	}

	@Override
	public void saveBookBorrow(Book book, LocalDate borrowedAt) {
		borrowedBooks.put(book, borrowedAt);
	}
	
	@Override
	public void makeBookUnavailable(long isbnCode){
		ISBN isbn = new ISBN(isbnCode);
		availableBooks.remove(isbn);
	}
	
	@Override
	public void makeBookAvailable(Book book){
		borrowedBooks.remove(book);
		addBooks(Collections.singletonList(book));
	}

	@Override
	public LocalDate findBorrowedBookDate(Book book) {
		return borrowedBooks.get(book);
	}
}
