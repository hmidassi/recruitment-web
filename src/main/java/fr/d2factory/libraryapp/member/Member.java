package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.book.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * A member is a person who can borrow and return books to a {@link Library}
 * A member can be either a student or a resident
 */
public abstract class Member {
    /**
     * An initial sum of money the member has
     */
    protected float wallet;
    
    private LocalDate entryDate;
    
    private List<Book> memberBorrowedBooks=new ArrayList<>();
    
    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays);
    
    public abstract boolean isLate(int numberOfDays);

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}

	public List<Book> getMemberBorrowedBooks() {
		if(memberBorrowedBooks==null){
			return new ArrayList<>();
		}
		return memberBorrowedBooks;
	}

	public void setMemberBorrowedBooks(List<Book> memberBorrowedBooks) {
		this.memberBorrowedBooks = memberBorrowedBooks;
	}
    
    
}
