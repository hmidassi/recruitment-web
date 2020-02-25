package fr.d2factory.libraryapp.member;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FirstYearStudentMember extends StudentDecorator {

	private static final int NUMBER_OF_FREE_BORROWING_DAYS = 15;

	public FirstYearStudentMember(StudentMember studentMember) {
		super(studentMember);
		// TODO Auto-generated constructor stub
	}



	
	@Override
	public void payBook(int numberOfDays) {
		if(numberOfDays>NUMBER_OF_FREE_BORROWING_DAYS){
			BigInteger numberOfTaxedDays=BigInteger.valueOf(numberOfDays)
					.subtract(BigInteger.valueOf(NUMBER_OF_FREE_BORROWING_DAYS));
			super.payBook(numberOfTaxedDays.intValue());
		}
	}
}
