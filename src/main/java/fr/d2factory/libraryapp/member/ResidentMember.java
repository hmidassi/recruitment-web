package fr.d2factory.libraryapp.member;

import java.math.BigDecimal;

public class ResidentMember extends Member {


	private static final float TAX_BEFORE_LATE=0.1f;
	private static final float TAX_AFTER_LATE=0.2f;
	private static final int MAX_DAYS_BEFORE_LATE=60;
	
	@Override
	public void payBook(int numberOfDays) {
		BigDecimal totalTax;
		BigDecimal newWallet;
		if(numberOfDays<=MAX_DAYS_BEFORE_LATE){
		totalTax=BigDecimal.valueOf(TAX_BEFORE_LATE)
				.multiply(BigDecimal.valueOf(numberOfDays));
		newWallet=BigDecimal.valueOf(getWallet()).subtract(totalTax);
		}else{
			totalTax=(BigDecimal.valueOf(TAX_BEFORE_LATE)
					.multiply(BigDecimal.valueOf(MAX_DAYS_BEFORE_LATE)))
					.add(BigDecimal.valueOf(TAX_AFTER_LATE)
					.multiply(BigDecimal.valueOf(numberOfDays - MAX_DAYS_BEFORE_LATE)));
			newWallet=BigDecimal.valueOf(getWallet()).subtract(totalTax);
			
		}
		setWallet(newWallet.floatValue());

	}

	@Override
	public boolean isLate(int numberOfDays) {
		
		return numberOfDays>MAX_DAYS_BEFORE_LATE;
	}


}
