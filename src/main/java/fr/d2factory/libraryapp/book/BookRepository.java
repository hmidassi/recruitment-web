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

    public void addBooks(List<Book> books){
        Map<ISBN,Book> booksToMap=books.stream()
        		.collect(Collectors.toMap(Book::getIsbn, Function.identity()));
        availableBooks.putAll(booksToMap);
    }

    public Book findBook(long isbnCode) {
        ISBN isbn=new ISBN(isbnCode);
        return availableBooks.get(isbn);
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        borrowedBooks.put(book, borrowedAt);
    }

    public LocalDate findBorrowedBookDate(Book book) {
        return borrowedBooks.get(book);
    }
}
