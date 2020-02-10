package fr.d2factory.libraryapp.member;

import java.math.BigDecimal;

public class ResidentMember extends Member {

	private static final float TAX_BEFORE_LATE=0.1f;
	
	@Override
	public void payBook(int numberOfDays) {
		BigDecimal totalTax=BigDecimal.valueOf(TAX_BEFORE_LATE).multiply(BigDecimal.valueOf(numberOfDays));
		BigDecimal newWallet=BigDecimal.valueOf(getWallet()).subtract(totalTax);
		setWallet(newWallet.floatValue());
	}

}
