package fr.d2factory.libraryapp.library;

import java.time.LocalDate;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(BookRepository bookRepository) {
		setBookRepository(bookRepository);
		
	}

}
