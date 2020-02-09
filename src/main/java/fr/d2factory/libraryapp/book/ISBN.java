package fr.d2factory.libraryapp.book;

import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if(obj==this){
			return true;
		}
		if(!(obj instanceof ISBN)){
			return false;
		}
		
		ISBN isbnObj= (ISBN) obj;
		return Objects.equals(this.isbnCode, isbnObj.isbnCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbnCode);
	}
    
	
    
}
