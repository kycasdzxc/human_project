package domain;

/**
 * 도서관에 등록되어 있는 도서 데이터. '도서번호'로 관리한다.
 * 담당 : 차민지
 */

public class Book {
	
	// 변수
	private int id; 			// 도서번호 : Book 클래스의 기본키
	private String title; 		// 책제목
	private String author; 		// 저자
	private String publisher; 	// 출판사
	private String isbn; 		// ISBN
	private int amount = 0; 	// 재고 : 도서 대여·반납 또는 도서 데이터 추가·삭제 시 수량 변경
	
	// 생성자
	public Book() {} // 기본 생성자

	public Book(int id, String title, String author, String publisher, String isbn) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}

	// 겟터, 셋터 : 데이터 캡슐화	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}