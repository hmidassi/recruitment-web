package fr.d2factory.libraryapp.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ISBN {
	
	
    long isbnCode;

    
    //added to allow the deserialization of books.json
    public ISBN() {
	}

	public ISBN(long isbnCode) {
        this.isbnCode = isbnCode;
    }

	public long getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(long isbnCode) {
		this.isbnCode = isbnCode;
	}
    
    
}
