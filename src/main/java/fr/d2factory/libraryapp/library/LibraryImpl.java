package fr.d2factory.libraryapp.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;

public class LibraryImpl implements Library {
	
	private BookRepository bookRepository;
	
	
	

	public BookRepository getBookRepository() {
		return bookRepository;
	}

	public void setBookRepository(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException, UnavailableBookException {
		Book book=bookRepository.findBook(isbnCode);
		if (book==null){
			throw new UnavailableBookException();
		}
		System.out.println("Nb of books already borrowed: " + String.valueOf(member.getMemberBorrowedBooks().size()));
		for(Book b: member.getMemberBorrowedBooks()){
			LocalDate borrowingDate=bookRepository.findBorrowedBookDate(b);
			System.out.println("Book borrowing date: " + borrowingDate.toString());
			int days=(int) ChronoUnit.DAYS.between(borrowingDate, LocalDate.now());
			if(member.isLate(days)){
				throw new HasLateBooksException();
			}
		}
		member.getMemberBorrowedBooks().add(book);
		bookRepository.saveBookBorrow(book, borrowedAt);
		bookRepository.makeBookUnavailable(isbnCode);
		return book;
	}

	@Override
	public void returnBook(Book book, Member member) {
		LocalDate borrowingDate=bookRepository.findBorrowedBookDate(book);
		int days=(int) ChronoUnit.DAYS.between(borrowingDate, LocalDate.now());
		member.payBook(days);
		member.getMemberBorrowedBooks().remove(book);
		bookRepository.makeBookAvailable(book);
	}	

	@Override
	public void initialize(BookRepository bookRepository) {
		setBookRepository(bookRepository);
		
	}

}
