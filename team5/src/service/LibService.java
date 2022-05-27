package service;

import java.text.ParseException;
import java.util.List;

import domain.Book;

/**
 * LibEx에서 구현될 interface
 * 
 */

public interface LibService {
	
	// 차민지[담당: Book 기능]
	
	void findBook();						// 재고 조회 : 보유 도서 조회
	
	List<Book> searchBook();				// 도서 검색 : 제목, 저자, 출판사
	
	void regBook();							// 소장도서 데이터 생성
	
	void modifyBook();						// 소장도서 데이터 수정
	
	void removeBook();		 				// 소장도서 데이터 삭제
	
	void searchList(List<Book> bookShelf);	// 도서 검색 값 리스트 양식 적용(일반 계정용)
	
	
	// 김동엽(조장)[담당: User 기능]
	
	void userList();  						// 계정 데이터 목록 조회
	
	void regUser(); 						// 계정 데이터 생성
	
	void modifyUser(); 						// 계정 데이터 수정
	
	void removeUser(); 						// 계정 데이터 삭제
	
	void admin(int userID); 				// 관리자권한 부여/회수
	
	void blackList(int userID);				// 블랙리스트 등록/해제
	
	int logIn(int userID, String userPW);	// 로그인 : 일반 계정, 관리자 계정 분리
	
	void logOut();							// 로그아웃
	
	
	// 김예찬[담당: Rent 기능, 예외처리(exception)]
	
	void rentBook(int userID); 				// 소장도서 대여
	
	void returnBook(int userID)				// 소장도서 반납
					throws ParseException;
	
	void rentList(int userID);				// 도서 대여·반납 기록 조회
	
	
	// EasterEgg
	
	void eggBook();	// (가상의) 박종민 선생님 저서 3권 출력
	
	void eggWord(); // 10개의 명언 중 하나를 무작위로 출력
}