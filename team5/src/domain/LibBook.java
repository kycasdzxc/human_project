package domain;

/**
 * 도서관에서 보유하고 있는 도서 데이터. '소장번호'로 관리한다.
 * 담당 : 김예찬, 차민지
 */

public class LibBook { // 실제 도서관에 있는 책
	
	// 변수
	private int id = 10010;	// 소장번호 : LibBook 클래스의 기본키
	private boolean rent;	// 대여여부(false: 대여가능, true: 대여중)
	private int bookID;		// Book 클래스의 도서번호
	
	// 생성자
	public LibBook() {} // 기본 생성자
	
	public LibBook(int id, int bookID) { // 데이터 생성 시, rent의 기본값은 false(대여가능)
		super();
		this.id = id;
		this.bookID = bookID;
	}
	
	// 겟터, 셋터 : 데이터 캡슐화	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public boolean isRent() {
		return rent;
	}

	public void setRent(boolean rent) {
		this.rent = rent;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
}