package fr.d2factory.libraryapp.member;

import java.math.BigDecimal;

public class StudentMember extends Member {

	protected static final float TAX=0.1f;
	

	
	@Override
	public void payBook(int numberOfDays) {
		BigDecimal totalTax=BigDecimal.valueOf(TAX).multiply(BigDecimal.valueOf(numberOfDays));
		BigDecimal newWallet=BigDecimal.valueOf(getWallet()).subtract(totalTax);
		setWallet(newWallet.floatValue());
	}

}
