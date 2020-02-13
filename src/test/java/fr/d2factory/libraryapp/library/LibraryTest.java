package fr.d2factory.libraryapp.library;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.FirstYearStudentMember;
import fr.d2factory.libraryapp.member.ResidentMember;
import fr.d2factory.libraryapp.member.StudentMember;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Do not forget to consult the README.md :)
 */
public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;
    private static List<Book> books;


    @BeforeEach
    void setup() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        File booksJson = new File("src/test/resources/books.json");
        books = mapper.readValue(booksJson, new TypeReference<List<Book>>() {
        });
        bookRepository=new BookRepository();
        library=new LibraryImpl();
        library.initialize(bookRepository);
        bookRepository.addBooks(books);
        
    }

    @Test
    void member_can_borrow_a_book_if_book_is_available() throws HasLateBooksException, UnavailableBookException{
    	/*dans le cas présent, le comportement d'emprunt sera identique
    	quel que soit le type de membre*/
    	
    	
        ResidentMember member = new ResidentMember();
        Book book=bookRepository.findBook(Long.valueOf("46578964513"));
        
        library.borrowBook(Long.valueOf("46578964513"), member, LocalDate.now());
        Assertions.assertTrue(bookRepository.findBorrowedBookDate(book)!=null);

        //trying to borrow a non-existent book
        Assertions.assertThrows(UnavailableBookException.class, 
        		()->library.borrowBook(Long.valueOf("12344"), member, LocalDate.now()));
        
    }
    
    @Test
    void borrowed_book_is_no_longer_available() throws  HasLateBooksException, UnavailableBookException{
    	ResidentMember member = new ResidentMember();
        library.borrowBook(Long.valueOf("46578964513"), member, LocalDate.now());
        Assertions.assertNull(bookRepository.findBook(Long.valueOf("46578964513")));
    }

    @Test
    void residents_are_taxed_10cents_for_each_day_they_keep_a_book() throws  HasLateBooksException, UnavailableBookException{
    	ResidentMember member = new ResidentMember();
    	member.setWallet(55);
    	LocalDate now=LocalDate.now();
    	LocalDate tenDaysAgo=now.minusDays(10);
    	Book book=bookRepository.findBook(Long.valueOf("46578964513"));
    	library.borrowBook(Long.valueOf("46578964513"), member, tenDaysAgo);
    	library.returnBook(book, member);
    	Assertions.assertEquals(54, member.getWallet());
    }

    @Test
    void students_pay_10_cents_the_first_30days() throws HasLateBooksException, UnavailableBookException{
    	StudentMember member = new StudentMember();
    	member.setWallet(55);
    	LocalDate now=LocalDate.now();
    	LocalDate tenDaysAgo=now.minusDays(30);
    	Book book=bookRepository.findBook(Long.valueOf("46578964513"));
    	library.borrowBook(Long.valueOf("46578964513"), member, tenDaysAgo);
    	library.returnBook(book, member);
    	Assertions.assertEquals(52, member.getWallet());
    }

    @Test
    void students_in_1st_year_are_not_taxed_for_the_first_15days() throws HasLateBooksException, UnavailableBookException{
    	StudentMember member = new StudentMember();
    	StudentMember firstYearMember=new FirstYearStudentMember(member);
    	firstYearMember.setWallet(55);
    	LocalDate now=LocalDate.now();
    	LocalDate tenDaysAgo=now.minusDays(30);
    	Book book=bookRepository.findBook(Long.valueOf("46578964513"));
    	library.borrowBook(Long.valueOf("46578964513"), firstYearMember, tenDaysAgo);
    	library.returnBook(book, firstYearMember);
    	Assertions.assertEquals(53.5, firstYearMember.getWallet());
    }
    
    @Test
    void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        Assertions.fail("Implement me");
    }

    @Test
    void members_cannot_borrow_book_if_they_have_late_books() throws HasLateBooksException, UnavailableBookException{
    	ResidentMember member = new ResidentMember();
        
        
        library.borrowBook(Long.valueOf("46578964513"), member, LocalDate.now().minusDays(60));

        //trying to borrow a non-existent book
        Assertions.assertThrows(HasLateBooksException.class, 
        		()->library.borrowBook(Long.valueOf("12344"), member, LocalDate.now()));
    }
}
