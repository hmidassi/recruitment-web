package fr.d2factory.libraryapp.member;

public class ResidentMember extends Member {

	
	private static final int MAX_DAYS_BEFORE_LATE=60;
	@Override
	public void payBook(long numberOfDays) {
		for(long i=1;i<=numberOfDays;i++){
			if(i<=MAX_DAYS_BEFORE_LATE){
				wallet=wallet-0.1f;
			} else {
				wallet=wallet-0.2f;
			}
		}

	}

}
