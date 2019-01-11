package student.interfaces;

public class ReservableBook
	extends Book
	implements Reservable {

	private Member reserver;
	
	public ReservableBook(String title, String author, String isbn, Genre genre) {
		super(title, author, isbn, genre);
		// TODO Auto-generated constructor stub
	}

	public boolean isReserved() {
		return (reserver != null);
	}

	
	@Override
	public boolean canBeReservedFor(Member member) {
		return canBeBorrowedBy(member);
	}

	@Override
	public boolean reserveItemFor(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

}
