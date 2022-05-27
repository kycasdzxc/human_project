package domain;

/**
 * 도서 대여·반납을 관리하는 데이터.
 * 담당 : 김예찬
 */

public class Rent {

	// 변수
	private int rentNum = 1001;	// 대여번호 : Rent 클래스의 기본키
	
	private int userID;			// 도서를 대여한 계정의 ID
	private int libBookID;		// 대여된 도서의 소장번호

	private long dateRent = System.currentTimeMillis(); // 대여일
	private long dateReturn = 0;						// 반납일: 대여일 + 7일
	
	// 기한 내에 반납처리가 되지 않은 책은 '연체'가 된다.

	// 생성자
	public Rent() {} // 기본 생성자
		
	public Rent(int rentNum, int userID, int libID) {
		super();
		this.rentNum = rentNum;
		this.userID = userID;
		this.libBookID = libID;
	}
	
	// 겟터, 셋터 : 데이터 캡슐화
	public int getRentNum() {
		return rentNum;
	}

	public void setRentNum(int rentNum) {
		this.rentNum = rentNum;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getLibBookID() {
		return libBookID;
	}

	public void setLibBookID(int libID) {
		this.libBookID = libID;
	}

	public long getDateRent() {
		return dateRent;
	}

	public void setDateRent(long dateRent) {
		this.dateRent = dateRent;
	}
	public long getDateReturn() {
		return dateReturn;
	}
	public void setDateReturn(long dateReturn) {
		this.dateReturn = dateReturn;
	}
}