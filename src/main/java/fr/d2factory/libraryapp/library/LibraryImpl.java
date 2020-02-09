package fr.d2factory.libraryapp.library;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.DoubleArraySerializer;

public class LibraryImpl implements Library {
	
	private BookRepository bookRepository;
	
	
	

	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
		Book book=bookRepository.findBook(isbnCode);
		if (book==null){
			throw new UnavailableBookException();
		}
		member.getMemberBorrowedBooks().add(book);
		bookRepository.saveBookBorrow(book, borrowedAt);
		bookRepository.makeBookUnavailable(isbnCode);
		return book;
	}

	@Override
	public void returnBook(Book book, Member member) {
		
		
		long daysBorrowed=ChronoUnit.DAYS.between(bookRepository.findBorrowedBookDate(book)
				, LocalDate.now());
		member.payBook(daysBorrowed);
		member.getMemberBorrowedBooks().remove(book);
		bookRepository.makeBookAvailable(book);
	}

	@Override
	public void initialize(BookRepository bookRepository) {
		setBookRepository(bookRepository);
		
	}

}
