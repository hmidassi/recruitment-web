package fr.d2factory.libraryapp.member;

public abstract class StudentDecorator extends StudentMember {

	private StudentMember studentMember;

	public StudentDecorator(StudentMember studentMember) {
		this.studentMember = studentMember;
	}

	public float getWallet() {
		return studentMember.getWallet();
	}

	public void setWallet(float wallet) {
		studentMember.setWallet(wallet);
	}

	@Override
	public void payBook(int numberOfDays) {
		studentMember.payBook(numberOfDays);
	}

}
