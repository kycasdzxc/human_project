package service;

import static util.LibUtil.nextInt;
import static util.LibUtil.nextLine;
import static util.LibUtil.convert;
import static util.LibUtil.convertLeft;
import static util.LibUtil.getKorCnt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import domain.Book;
import domain.LibBook;
import domain.Rent;
import domain.User;

public class LibServiceImpl implements LibService {

	/** @Book_관련_메서드 */

	/**
	 * 도서관에 등록되어 있는 도서의 목록을 보여주는 메서드. 재고 확인 가능.
	 */
	private void bookList() {
		bookIndex();
		for (Book b : books) {
			System.out.print("│     " + convert(b.getId() + "", 3) + "    │ ");
			System.out.print(convertLeft(bookTitleLength(b), 24) + " │ ");
			System.out.print(convertLeft(bookAuthorLength(b), 20) + " │ ");
			System.out.print(convertLeft(bookPublisherLength(b), 20) + " │ ");
			System.out.print(convertLeft(b.getIsbn(), 10) + " │  ");
			System.out.println(convert(b.getAmount() + "", 2) + "권  │");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
		}
	}

	/**
	 * 신규 도서 등록 시, 중복된 도서번호가 있는지 확인하는 메서드. 해당 도서번호가 존재할 경우, 0을 반환한다.
	 * 
	 * @param bookID : 확인할 도서번호(Book 클래스의 id)
	 * @return bookID : 중복이 없을 경우, 도서번호 반환. 중복이 있을 경우, 0 반환
	 */
	private int duplBookID(int bookID) {
		for (Book b : books) {
			if (bookID == b.getId()) {
				bookID = 0;
				break;
			}
		}
		return bookID;
	}

	/**
	 * 소장도서 삭제 시, 해당 도서의 대여기록을 삭제해주는 메서드.
	 * 
	 * @param scrapKey : 삭제하려는 소장도서 번호(LibBook 클래스의 id)
	 */
	private void removeRents(int scrapKey) {
		for (int i = 0, cnt = 0; i < rents.size() - cnt; i++) {
			if (rents.get(i).getLibBookID() == scrapKey) {
				rents.remove(i);
				cnt++;
			}
		}
	}

	/**
	 * 문자의 공백을 제거하고, 영문자를 소문자로 변경하는 메서드.
	 * 
	 * @param s : 검색할 내용의 문자열
	 * @return s : 공백 제거, 영문자는 소문자로 변경
	 */
	private String transWord(String s) {
		s = s.toLowerCase().replaceAll(" ", "");
		return s;
	}

	/**
	 * 기존 도서목록 중 검색내용이 도서정보(제목, 저자, 출판사) 안에 포함된 도서를 찾아주는 메서드.
	 * 
	 * @param keyword : 검색할 내용의 문자열
	 * @param kind : 검색방식(1.제목, 2.저자, 3.출판사)
	 * @return books : 검색내용과 일치한 도서들을 Book 타입의 List 형식으로 반환
	 */
	private List<Book> findKindBy(String keyword, int kind) {
		List<Book> books = new ArrayList<Book>(); // 검색관리를 위한 List생성
		keyword = transWord(keyword); // 검색내용

		for (Book b : this.books) {
			
			switch (kind) { // 기존 도서목록의 제목과 일치하는 것이 있는지 확인
			
			case 1: // 제목으로 탐색
				if (transWord(b.getTitle()).contains(keyword)) {
					books.add(b);
				}
				break;
				
			case 2: // 저자로 탐색
				if (transWord(b.getAuthor()).contains(keyword)) {
					books.add(b);
				}
				break;
				
			case 3: // 출판사로 탐색
				if (transWord(b.getPublisher()).contains(keyword)) {
					books.add(b);
				}
				break;
				
			default:
				break;
			}
		}
		return books;
	}

	/**
	 * 검색한 도서의 소장 여부를 확인하는 메서드.
	 * 
	 * @param word  : 검색할 내용의 문자열
	 * @param index : 검색방식(1.제목, 2.저자, 3.출판사)
	 * @return 해당 도서가 존재할 경우, List 형식의 도서목록 반환
	 */
	private List<Book> searchBookIf(String word, int index) { // 소장 여부 확인
		List<Book> bookShelf = findKindBy(word, index);
		
		if (bookShelf.isEmpty()) {
			System.out.println("소장되지 않은 도서입니다.");
		}
		return bookShelf;
	}

	/**
	 * 도서 데이터를 목록화하여 전체재고조회(1)와 도서번호검색(2)으로 출력하는 메서드.
	 */
	public void findBook() { // 사서(관리자)용 기능
		int storageIndex = nextInt("1.전체재고조회 2.도서번호검색 > ", 1, 2);
		
		switch (storageIndex) {
		
		case 1:
			bookList();
			break;

		case 2:
			List<LibBook> booksInven = findLibBookListByBookID(nextInt("조회할 도서의 도서번호를 입력해주세요. > "));
			if (booksInven.size() > 0) { // 북ID(책번호)가 없을 경우
				libBookIndex();
				for (LibBook lB : booksInven) {
					System.out.print("│     " + convert(lB.getBookID() + "", 3) + "    │ ");
					System.out.print("  " + convert(lB.getId() + "", 3) + "    │ ");
					System.out.print(convertLeft(bookTitleLength(findBookByID(lB.getBookID())), 24) + " │ ");
					System.out.print(convertLeft(bookAuthorLength(findBookByID(lB.getBookID())), 20) + " │ ");
					System.out.print(convertLeft(bookPublisherLength(findBookByID(lB.getBookID())), 20) + " │   ");
					System.out.println(convert(checkRentState(findLibBookByID(lB.getId())), 4) + "   │");
					System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
				}
			} else {
				System.out.println("존재하지 않는 도서번호입니다.");
				return;
			}
			break;

		default:
			System.out.println("올바른 번호를 입력하세요.");
			break;
		} // switch
	}

	/**
	 * 도서를 제목(1), 저자(2), 출판사(3)로 검색하는 메서드.
	 * @return bookShelf : 검색한 도서정보를 Book 타입의 List로 반환
	 */
	public List<Book> searchBook() {
		int index = nextInt("1. 제목 검색 2. 저자 검색 3. 출판사 검색 > ", 1, 3);

		List<Book> bookShelf = null;
		
		switch (index) {
		
		case 1: // 제목으로 검색
			String word = nextLine("제목을 입력해주세요. > ");
			bookShelf = searchBookIf(word, index);
			break;
			
		case 2: // 저자로 검색
			String word2 = nextLine("저자를 입력해주세요. > ");
			bookShelf = searchBookIf(word2, index);
			break;
			
		case 3: // 출판사로 검색
			String word3 = nextLine("출판사를 입력해주세요. > ");
			bookShelf = searchBookIf(word3, index);
			break;
			
		default:
			System.out.println("올바른 번호를 입력하세요.");
			break;
		}
		return bookShelf;
	}

	/**
	 * 소장도서 데이터를 등록하는 메서드. 새로운 책 추가(1) 시, Book 클래스에도 데이터가 생성된다.
	 */
	public void regBook() { // 사서(관리자)용 기능
		int regIndex = nextInt("1.새로운 책 추가 2.기존의 책 추가 > ", 1, 2);

		switch (regIndex) {
		
		case 1:
			int regKey = duplBookID(nextInt("추가할 책의 도서번호 > ")); // 도서번호(bookID) 입력

			switch (regKey) {
			
			case 0: // 추가할 책의 번호가 이미 존재하는지 확인
				System.out.println("존재하는 도서번호입니다.");
				break;

			default:
				books.add(new Book(regKey, nextLine("제목 > ", true, false), nextLine("저자 > ", true, false),
									nextLine("출판사 > ", true, false), nextLine("ISBN > ", false, true)));
				lBooks.add(new LibBook(lBooks.get(lBooks.size() - 1).getId() + 1, regKey));

				Book enrollBook = findBookByID(regKey); // 재고 증가
				enrollBook.setAmount(enrollBook.getAmount() + 1);
				break;
			}
			break;

		case 2:
			System.out.println("기존의 책을 추가합니다.");
			Book addBook = findBookByID(nextInt("추가할 도서의 도서번호 > "));
			
			if (addBook != null) {
				lBooks.add(new LibBook(lBooks.get(lBooks.size() - 1).getId() + 1, addBook.getId()));
				addBook.setAmount(addBook.getAmount() + 1);
				break;
			} else {
				System.out.println("존재하지 않는 도서번호입니다.");
				break;
			}

		default:
			System.out.println("올바른 번호를 입력하세요.");
			break;
		}
	}

	/**
	 * 소장도서 데이터를 수정하는 메서드. ISBN으로 수정할 도서의 정보를 찾는다.
	 */
	public void modifyBook() { // 사서(관리자)용 기능

		Book alterbook = findBookByIsbn(nextLine("변경할 책의 ISBN > "));
		
		if (alterbook == null) {
			System.out.println("존재하지 않는 ISBN입니다.");
			return;
		}
		// 수정하는 내용 등록
		alterbook.setTitle(nextLine("이름 > ", true, false));
		alterbook.setAuthor(nextLine("저자 > ", true, false));
		alterbook.setPublisher(nextLine("출판사 > ", true, false));
		alterbook.setIsbn(nextLine("ISBN > ", false, true));

		System.out.println("책이 수정되었습니다.");
	}
	
	/**
	 * 소장도서 데이터를 삭제하는 메서드. 대여 중일 경우, 데이터 삭제는 불가하다.
	 */
	public void removeBook() { // 사서(관리자)용 기능

		List<LibBook> scrapBooks = findLibBookListByBookID(nextInt("삭제할 책의 도서번호 > ")); // BookID로 접근하여 libBookID 검색
		
		if (scrapBooks.size() > 0) { // 북ID(책번호)가 없을 경우
			libBookIndex();
			for (LibBook lB : scrapBooks) {
				System.out.print("│     " + convert(lB.getBookID() + "", 3) + "    │ ");
				System.out.print("  " + convert(lB.getId() + "", 3) + "    │ ");
				System.out.print(convertLeft(bookTitleLength(findBookByID(lB.getBookID())), 24) + " │ ");
				System.out.print(convertLeft(bookAuthorLength(findBookByID(lB.getBookID())), 20) + " │ ");
				System.out.print(convertLeft(bookPublisherLength(findBookByID(lB.getBookID())), 20) + " │   ");
				System.out.println(convert(checkRentState(findLibBookByID(lB.getId())), 4) + "   │");
				System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
			}

			int scrapKey = nextInt("삭제할 책의 소장번호 > ");
			
			if (findLibBookByID(scrapKey) == null) {
				System.out.println("소장번호를 확인해주세요.");
			}
			else if (findLibBookByID(scrapKey) != null && findLibBookByID(scrapKey).isRent() == false) {
				
				Book disuseBook = findBookByLibBookID(scrapKey); // 재고 감소
				disuseBook.setAmount(disuseBook.getAmount() - 1);

				removeRents(scrapKey);

				lBooks.remove(findLibBookIndexBy(scrapKey)); // 상단에 출력된 libBookID 중 선택하여 삭제
				System.out.println("책이 삭제되었습니다.");
				
			} else if (findLibBookByID(scrapKey).isRent() == true) {
				System.out.println("대여 중인 도서입니다.");
			}
			
		} else {
			System.out.println("존재하지 않는 도서번호입니다.");
		}
	}

	/**
	 * searchBook()에서 반환 받은 Book 타입의 List를 도서목록으로 출력하는 메서드.
	 * @param bookShelf : searchBook()에서 반환 받은 List 형식의 도서목록
	 */
	public void searchList(List<Book> bookShelf) { // 이용자 기능
		if (bookShelf.size() > 0) {
			bookIndex();
			for (Book b : bookShelf) {
				System.out.print("│     " + convert(b.getId() + "", 3) + "    │ ");
				System.out.print(convertLeft(bookTitleLength(b), 24) + " │ ");
				System.out.print(convertLeft(bookAuthorLength(b), 20) + " │ ");
				System.out.print(convertLeft(bookPublisherLength(b), 20) + " │ ");
				System.out.print(convertLeft(b.getIsbn(), 10) + " │  ");
				System.out.println(convert(b.getAmount() + "", 2) + "권  │");
				System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			}
		}
	}

	/** @User_관련_메서드 */

	/**
	 * 해당 계정이 '관리자'인지 '블랙리스트'인지 확인하는 메서드.
	 * isAdmin()이 true면 '관리자', isBlacklist()가 true면 '블랙리스트'를 문자열로 반환한다.
	 * '관리자'와 '블랙리스트'는 중첩될 수 없다.
	 * @param userID : 조회할 계정의 ID(User 클래스의 id)
	 * @return isAdmin()이 true면 '관리자', isBlacklist()가 true면 '블랙리스트'를 문자열로 반환
	 */
	private String userState(int userID) {
		if (findUserByID(userID).isAdmin() == true) {
			return "  관리자";
		} else if (findUserByID(userID).isBlacklist() == true) {
			return "블랙리스트";
		} else {
			return "";
		}
	}

	/**
	 * 가입된 계정의 목록을 보여주는 메서드.
	 */
	public void userList() {
		userIndex();
		for (User u : users) {
			System.out.print("│  " + convertLeft(userNameLength(u), 13) + "   │    ");
			System.out.print(convert(u.getUserBirth(), 6) + "    │ ");
			System.out.print(convert(u.getUserPN(), 13) + " │  ");
			System.out.println(convertLeft(userState(u.getId()), 10) + "  │");
			System.out.printf("──────────────────────────────────────────────────────────────────%n");
		}
	}

	/**
	 * 신규 계정의 데이터를 등록하는 메서드. 생년월일은 6글자, 전화번호는 11글자를 입력해야 한다.
	 */
	public void regUser() {
		users.add(new User(nextLine("이름 > ", true, false), birthLength(nextLine("생년월일[ex)921024] > ", false, true)),
				pnLength(nextLine("전화번호[ex)01086940273] > ", false, true)), nextInt("ID > "), nextLine("PW > "),
				false));

		System.out.println("계정생성이 완료되었습니다.");
	}

	/**
	 * 계정 데이터를 수정하는 메서드. 계정 ID로 수정할 계정의 정보를 찾는다.
	 */
	public void modifyUser() {
		User user = findUserByID(nextInt("수정할 계정의 ID > "));
		if (user != null) {
			System.out.println(user);

			user.setUserName(nextLine("Enter the name to be replaced. > ", true, false));
			user.setUserBirth(nextLine("Enter the birth to be replaced. > ", false, true));
			user.setUserPN(nextLine("Enter the P.N to be replaced. >", false, true));
			System.out.println("등록 완료 되었습니다.");
			System.out.println(user);

		} else {
			System.out.println("등록된 적 없는 ID입니다.");
		}
	}

	/**
	 * 계정 데이터를 삭제하는 메서드. 해당 계정이 대여 중인 도서가 있거나, 관리자 계정일 경우 삭제가 불가하다.
	 */
	public void removeUser() {
		User user = findUserByID(nextInt("삭제할 회원의 ID를 입력하세요 > "));
		int userID = 0;
		for (Rent r : rents) {
			if (user.getId() == r.getUserID()) {
				userID = r.getUserID();
				break;
			}
		}
		// userID가 없는 아이디일 경우
		if (user == null) {
			System.out.println("잘못된 ID입니다.");
		// 대여 중인 도서가 있는 경우
		} else if (userID != 0) {
			System.out.println("대여 중인 도서가 있습니다.");
		// 관리자 계정일 경우
		} else if (user.isAdmin() == true) {
			System.out.println("해당 계정은 관리자 계정입니다.");
		} else {
			users.remove(user);
			System.out.println("**삭제완료**");
		}
	}

	/**
	 * 관리자 권한을 부여·회수하는 메서드. '관리자'는 '블랙리스트'와 중첩될 수 없다.
	 * @param userID : 관리자 권한을 부여·회수할 계정의 ID(User 클래스의 id)
	 */
	public void admin(int userID) {
		if (findUserByID(userID) != null && findUserByID(userID).getId() == userID) {
			if (findUserByID(userID).isAdmin() == false && findUserByID(userID).isBlacklist() == false) {
				findUserByID(userID).setAdmin(true);
				System.out.printf("%s님께 관리자 권한을 부여하였습니다.%n", findUserByID(userID).getUserName());
			} else if (findUserByID(userID).isAdmin() == false && findUserByID(userID).isBlacklist() == true) {
				System.out.println("해당 계정은 블랙리스트입니다.");
			} else {
				findUserByID(userID).setAdmin(false);
				System.out.printf("%s님의 관리자 권한을 회수하였습니다.%n", findUserByID(userID).getUserName());
			}
		} else {
			System.out.println("ID를 확인해주세요.");
		}
	}

	/**
	 * 블랙리스트로 등록·해제하는 메서드. '블랙리스트'는 '관리자'와 중첩될 수 없다.
	 * @param userID : 블랙리스트로 등록·해제할 계정의 ID(User 클래스의 id)
	 */
	public void blackList(int userID) {
		if (findUserByID(userID) != null && findUserByID(userID).getId() == userID) {
			if (findUserByID(userID).isBlacklist() == false && findUserByID(userID).isAdmin() == false) {
				findUserByID(userID).setBlacklist(true);
				System.out.printf("%s님이 블랙리스트로 등록 되었습니다.%n", findUserByID(userID).getUserName());
			} else if (findUserByID(userID).isBlacklist() == false && findUserByID(userID).isAdmin() == true) {
				System.out.println("관리자 계정입니다.");
			} else {
				findUserByID(userID).setBlacklist(false);
				System.out.println("블랙리스트가 해제되었습니다.");
			}
		} else {
			System.out.println("ID를 확인해주세요.");
		}
	}
	
	/**
	 * 로그인을 담당하는 메서드. 해당 계정이 일반 계정인지 관리자 계정인지 구분해준다.
	 * @param userID : 로그인할 계정의 ID(User 클래스의 id)
	 * @param userPW : 로그인할 계정의 PW(User 클래스의 pw)
	 * @return 관리자 계정은 1, 일반 계정은 2을 반환. id 또는 pw가 틀리면 0을 반환
	 */
	public int logIn(int userID, String userPW) {
		if (findUserByID(userID) != null) {
			if (findUserByID(userID).getPw().equals(userPW)) {
				// 관리자 계정일 경우
				if (findUserByID(userID).isAdmin() == true) {
					System.out.println();
					System.out.println("관리자 계정입니다.");
					return 1;
				// 일반 계정일 경우
				} else {
					System.out.println();
					System.out.printf("%s님, 안녕하세요.%n", findUserByID(userID).getUserName());
					return 2;
				}
			// PW가 일치하지 않는 경우
			} else {
				System.out.println();
				System.out.println("ID 또는 pw를 확인해주세요.");
				return 0;
			}
		// ID가 존재하지 않는 경우
		} else {
			System.out.println();
			System.out.println("ID 또는 pw를 확인해주세요.");
			return 0;
		}
	}

	/**
	 * 로그아웃을 담당하는 메서드.
	 */
	public void logOut() {
		System.out.println("로그아웃이 성공적으로 이루어졌습니다.");
	}

	/** @Rent_관련_메서드 */
	
	/**
	 * 대여 가능한 소장도서번호를 찾는 메서드. 대여 가능한 도서가 없을 경우, null을 반환한다.
	 * @param rBook : 대여하려는 도서의 도서정보(Book 타입)
	 * @return 대여 가능한 소장도서번호(LibBook 클래스의 id) 반환
	 */
	private int matchLibBook(Book rBook) {
		LibBook libBook = null;
		for (LibBook l : lBooks) {
			if (l.getBookID() == rBook.getId() && l.isRent() == false) {
				libBook = l;
				libBook.setRent(true);
				break;
			}
		}
		return libBook.getId();
	}

	/**
	 * 해당 도서를 대여상태로 바꿔주는 메서드.
	 * matchLibBook()에서 대여가능 도서를 조회하여
	 * 대여 가능한 도서가 있으면 해당 소장도서의 대여여부(LibBook 클래스의 rent)를 true로 변경한다.
	 * 해당 도서의 재고(Book 클래스의 amount)도 1 감소시킨다.
	 * @param rBook : 대여하려는 도서의 도서정보(Book 타입)
	 * @param userID : 도서를 대여하는 계정의 ID(User 클래스의 id)
	 */
	private void rent(Book rBook, int userID) {
		rents.add(new Rent(rents.get(rents.size() - 1).getRentNum(), userID, matchLibBook(rBook)));
		rBook.setAmount(rBook.getAmount() - 1);
		System.out.println("대여완료");
	}

	/**
	 * 소장도서의 대여여부를 문자열로 반환하는 메서드. 대여여부가 true면 "대여", false면 "보유"를 반환한다.
	 * @param libBook : 대여여부를 문자열로 반환하려는 소장도서정보(LibBook 타입)
	 * @return libBook.isRent()가 true면 "대여", false면 "보유"를 문자열로 반환
	 */
	private String checkRentState(LibBook libBook) {
		if (libBook.isRent() == true) {
			return "대여";
		} else {
			return "보유";
		}
	}

	/**
	 * 대여 중인 도서를 반납하면 대여상태를 '보유'로 바꿔주는 메서드.
	 * 반납이 완료되면 해당 도서의 재고(Book 클래스의 amount)도 1 증가시킨다.
	 * @param returnBook : 반납할 도서의 대여정보(Rent 타입)
	 */
	private void changeReturnState(Rent returnBook) {
		for (LibBook l : lBooks) {
			if (l.getId() == returnBook.getLibBookID()) {
				l.setRent(false);
				findBookByLibBookID(l.getId()).setAmount(findBookByLibBookID(l.getId()).getAmount() + 1);
			}
		}
	}

	/**
	 * 1/1000초(ms) 단위를 날짜형태로 변환해주는 메서드. 'yyyy-MM-dd'로 출력한다.
	 * @param time : 날짜형태로 변환할 시간(ms : 1/1000초 단위)
	 * @return 'yyyy-MM-dd' 양식의 문자열로 반환
	 */
	private String dateFormat(long time) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(time);
	}
	
	/**
	 * 반납일을 문자열로 반환하는 메서드. 반납되지 않은 도서는 공백으로 반환한다.
	 * @param rent : 반납일을 문자열로 반환할 대여정보(Rent 타입)
	 * @return 반납일이 기록된 도서는 반납일을, 반납되지 않은 도서는 공백으로 반환
	 */
	private String checkDateReturn(Rent rent) {
		if (rent.getDateReturn() == 0) {
			return "";
		} else {
			return dateFormat(rent.getDateReturn());
		}
	}
	
	/**
	 * 연체일을 계산하는 메서드. 대여일로부터 7일이 지나면 문자열 "연체"를 반환한다.
	 * @param rent : 연체일을 계산할 대여정보(Rent 타입)
	 * @return 대여일로부터 7일이 지나면 문자열 "연체" 반환
	 * @throws ParseException 문자열을 ms 단위로 변환할 때 발생하는 예외상황
	 */
	private String findOverdueByRent(Rent rent) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		long returnTime = dateFormat.parse(dateFormat.format(rent.getDateRent())).getTime() + 1000 * 60 * 60 * 24 * 7;

		if (System.currentTimeMillis() > returnTime) {
			return "연체";
		} else {
			return "";
		}
	}

	/**
	 * 대여하고자 하는 도서를 검색하고, 검색한 도서를 대여하는 메서드.
	 * @param userID : 도서를 대여하려는 계정의 ID(User 클래스의 id)
	 */
	public void rentBook(int userID) { // 대여 기능
		if (findUserByID(userID) != null) {
			System.out.println("대여할 도서를 검색해주세요.");
			List<Book> sBooks = searchBook();
			
			if (sBooks.size() > 0) {
				rentIndex();
				int cnt = 1;
				for (Book b : sBooks) {
					System.out.printf("│ %2d │ ", cnt++);
					System.out.print(convertLeft(bookTitleLength(b), 24) + " │ ");
					System.out.print(convertLeft(bookAuthorLength(b), 20) + " │ ");
					System.out.print(convertLeft(bookPublisherLength(b), 20) + " │  ");
					System.out.println(convert(b.getAmount() + "", 2) + "권  │");
					System.out.printf("────────────────────────────────────────────────────────────────────────────────────────%n");
				}
				int rentNum = nextInt("대여할 도서의 번호를 입력해주세요. > ") - 1;

				if (!(rentNum < 0 || rentNum > sBooks.size())) {

					Book rBook = sBooks.get(rentNum);
					bookIndex();
					System.out.print("│     " + convert(rBook.getId() + "", 3) + "    │ ");
					System.out.print(convertLeft(bookTitleLength(rBook), 24) + " │ ");
					System.out.print(convertLeft(bookAuthorLength(rBook), 20) + " │ ");
					System.out.print(convertLeft(bookPublisherLength(rBook), 20) + " │ ");
					System.out.print(convertLeft(rBook.getIsbn(), 10) + " │  ");
					System.out.println(convert(rBook.getAmount() + "", 2) + "권  │");
					System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────");

					if (nextInt("대여하시겠습니까?[1.네 2.아니오] > ") == 1) {
						// 대여할 도서가 있는 경우
						if (rBook.getAmount() > 0) {
							rent(rBook, userID);
						// 모든 도서가 대여 중일 경우
						} else {
							System.out.println("현재 모든 책이 대여 중입니다.");
						}
					}
				} else {
					System.out.println("잘못 입력하셨습니다.");
				}
			}
		} else {
			System.out.println("ID를 확인해주세요.");
		}
	}

	/**
	 * 대여한 도서목록을 확인하고 대여한 도서를 반납하는 메서드.
	 * @param userID : 도서를 반납하려는 계정의 ID(User 클래스의 id)
	 */
	public void returnBook(int userID) throws ParseException { // 반납 기능 >> userID 입력 필요
		System.out.println("도서반납입니다.");
		List<Rent> rBooks = new ArrayList<Rent>();
		int cnt = 1;
		rentListIndex();
		for (Rent r : rents) {
			if (r.getUserID() == userID /* logIn 한 userID */ && findLibBookByID(r.getLibBookID()).isRent() == true) {
				System.out.printf("│ %2d │   ", cnt++);
				System.out.print(convert(r.getRentNum() + "", 4) + "   │ ");
				System.out.print(convertLeft(bookTitleLength(findBookByLibBookID(r.getLibBookID())), 24) + " │ ");
				System.out.print(convertLeft(bookAuthorLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
				System.out.print(convertLeft(bookPublisherLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
				System.out.print(convertLeft(dateFormat(r.getDateRent()), 10) + " │    ");
				System.out.println(convertLeft(findOverdueByRent(r), 4) + "    │");
				System.out.printf("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
				rBooks.add(r);
			}
		}
		if (rBooks.size() > 0) {
			int returnNum = nextInt("반납할 도서의 번호를 입력해주세요. > ") - 1;
			if (!(returnNum < 0 || returnNum > rBooks.size())) {
				changeReturnState(rBooks.get(returnNum));
				rBooks.get(returnNum).setDateReturn(System.currentTimeMillis());
				System.out.println("반납완료");
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		} else {
			System.out.println();
			System.out.println("반납할 도서가 없습니다.");
		}
	}

	/**
	 * 도서대여이력을 출력하는 메서드. 대여여부 및 대여일, 반납일을 확인할 수 있다.
	 * 일반 계정은 자신의 도서대여이력만 볼 수 있고, 관리자 계정은 전체 도서대여이력을 볼 수 있다.
	 * @param userID : 대여이력을 확인하고자 하는 계정의 ID(User 클래스의 id)
	 */
	public void rentList(int userID) {
		System.out.println("도서대여이력입니다.");
		if (findUserByID(userID).isAdmin() == true) {
			listIndex();
			for (Rent r : rents) {
				System.out.print("│    " + convert(r.getRentNum() + "", 4) + "    │  ");
				System.out.print(convertLeft(userNameLength(findUserByID(r.getUserID())), 13) + "   │ ");
				System.out.print(convertLeft(bookTitleLength(findBookByLibBookID(r.getLibBookID())), 24) + " │ ");
				System.out.print(convertLeft(bookAuthorLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
				System.out.print(convertLeft(bookPublisherLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
				System.out.print(convertLeft(dateFormat(r.getDateRent()), 10) + " │ ");
				System.out.print(convertLeft(checkDateReturn(r), 10) + " │   ");
				System.out.println(convert(checkRentState(findLibBookByID(r.getLibBookID())), 4) + "   │");
				System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
			}
		} else {
			listIndex();
			for (Rent r : rents) {
				if (r.getUserID() == userID /* logIn 한 userID */) {
					System.out.print("│    " + convert(r.getRentNum() + "", 4) + "    │  ");
					System.out.print(convertLeft(userNameLength(findUserByID(userID)), 13) + "   │ ");
					System.out.print(convertLeft(bookTitleLength(findBookByLibBookID(r.getLibBookID())), 24) + " │ ");
					System.out.print(convertLeft(bookAuthorLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
					System.out
							.print(convertLeft(bookPublisherLength(findBookByLibBookID(r.getLibBookID())), 20) + " │ ");
					System.out.print(convertLeft(dateFormat(r.getDateRent()), 10) + " │ ");
					System.out.print(convertLeft(checkDateReturn(r), 10) + " │   ");
					System.out.println(convert(checkRentState(findLibBookByID(r.getLibBookID())), 4) + "   │");
					System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
				}
			}
		}
	}

	/** @findBy_메서드 */
	
	/**
	 * 소장도서 번호가 일치하는 소장도서의 인덱스를 찾는 메서드.
	 * @param libBookID : 소장도서 번호(LibBook 클래스의 id)
	 * @return ret : 소장도서 번호가 일치하는 소장도서의 인덱스 번호
	 */
	private int findLibBookIndexBy(int libBookID) {
		int ret = -1;
		for (int i = 0; i < lBooks.size(); i++) {
			if (lBooks.get(i).getId() == libBookID) {
				ret = i;
				break;
			}
		}
		return ret;
	}

	/**
	 * ISBN이 일치하는 도서정보를 찾는 메서드. 일치하는 값이 없을 경우, null을 반환한다.
	 * @param isbn : Book 클래스의 isbn과 비교할 isbn
	 * @return book : ISBN이 일치하는 도서정보를 Book 타입으로 반환
	 */
	private Book findBookByIsbn(String isbn) {
		Book book = null;
		for (Book b : books) {
			if (b.getIsbn().equals(isbn)) {
				book = b;
			}
		}
		return book;
	}

	/**
	 * 도서번호가 일치하는 도서정보를 찾는 메서드. 일치하는 값이 없을 경우, null을 반환한다.
	 * @param bookID : 도서번호(Book 클래스의 id)
	 * @return book : 도서번호가 일치하는 도서정보를 Book 타입으로 반환
	 */
	private Book findBookByID(int bookID) {
		Book book = null;
		for (Book b : books) {
			if (b.getId() == bookID) {
				book = b;
			}
		}
		return book;
	}

	/**
	 * 도서번호가 일치하는 소장도서를 목록화하는 메서드.
	 * @param bookID : 도서번호(Book 클래스의 id)
	 * @return libBooks : 도서번호가 일치하는 소장도서를 LibBook타입의 List형식으로 반환
	 */
	private List<LibBook> findLibBookListByBookID(int bookID) {
		List<LibBook> libBooks = new ArrayList<LibBook>();
		for (LibBook lB : lBooks) {
			if (lB.getBookID() == bookID) {
				libBooks.add(lB);
			}
		}
		return libBooks;
	}

	/**
	 * 소장도서 번호가 일치하는 소장도서 정보를 찾는 메서드. 일치하는 값이 없을 경우, null을 반환한다.
	 * @param libBookID : 소장도서 번호(LibBook 클래스의 id)
	 * @return libBook : 소장도서 번호가 일치하는 소장도서 정보를 LibBook 타입으로 반환
	 */
	private LibBook findLibBookByID(int libBookID) {
		LibBook libBook = null;
		for (LibBook lB : lBooks) {
			if (lB.getId() == libBookID) {
				libBook = lB;
				break;
			}
		}
		return libBook;
	}
	
	/**
	 * 소장도서 번호가 일치하는 도서정보를 찾는 메서드. 일치하는 값이 없을 경우, null을 반환한다.
	 * @param libBookID : 소장도서 번호(LibBook 클래스의 id)
	 * @return book : 소장도서 번호가 일치하는 도서정보를 Book 타입으로 반환
	 */
	private Book findBookByLibBookID(int libBookID) { // LibBookID로 Book 탐색
		Book book = null;
		for (Book b : books) {
			if (b.getId() == findLibBookByID(libBookID).getBookID()) {
				book = b;
			}
		}
		return book;
	}

	/**
	 * 계정 ID가 일치하는 계정정보를 찾는 메서드. 일치하는 값이 없을 경우, null을 반환한다.
	 * @param userID : 계정 ID(User 클래스의 id)
	 * @return user : 계정 ID가 일치하는 계정정보를 User 타입으로 반환
	 */
	private User findUserByID(int userID) { // UserID로 User 탐색
		User user = null;

		for (User u : users) {
			if (u.getId() == userID) {
				user = u;
			}
		}
		return user;
	}

	/** @문자열_관련_메서드 */
	
	/**
	 * 도서정보의 책제목을 축약하는 메서드. 책제목이 20글자가 넘어가면, 19번째 글자 이후는 ... 으로 출력된다.(반각문자 기준)
	 * @param book : 책제목을 축약할 도서의 정보(Book 타입)
	 * @return 반각문자(영문자, 숫자)는 최대 19글자, 전각문자(한글)는 최대 9글자까지 출력이 가능하다.
	 * 		   20글자가 넘어가면 ... 으로 출력된다.
	 */
	private String bookTitleLength(Book book) {
		int ko = getKorCnt(book.getTitle());
		if (book.getTitle().length() > 19 - ko) {
			return book.getTitle().substring(0, 19 - ko) + "...";
		} else {
			return book.getTitle();
		}
	}

	/**
	 * 도서정보의 저자명을 축약하는 메서드. 저자명이 16글자가 넘어가면, 15번째 글자 이후는 ... 으로 출력된다.(반각문자 기준)
	 * @param book : 저자명을 축약할 도서의 정보(Book 타입)
	 * @return 반각문자(영문자, 숫자)는 최대 15글자, 전각문자(한글)는 최대 7글자까지 출력이 가능하다.
	 * 		   16글자가 넘어가면 ... 으로 출력된다.
	 */
	private String bookAuthorLength(Book book) {
		int ko = getKorCnt(book.getAuthor());
		if (book.getAuthor().length() > 15 - ko) {
			return book.getAuthor().substring(0, 15 - ko) + "...";
		} else {
			return book.getAuthor();
		}
	}

	/**
	 * 도서정보의 출판사명을 축약하는 메서드. 출판사명이 16글자가 넘어가면, 15번째 글자 이후는 ... 으로 출력된다.(반각문자 기준)
	 * @param book : 출판사명을 축약할 도서의 정보(Book 타입)
	 * @return 반각문자(영문자, 숫자)는 최대 15글자, 전각문자(한글)는 최대 7글자까지 출력이 가능하다.
	 * 		   16글자가 넘어가면 ... 으로 출력된다.
	 */
	private String bookPublisherLength(Book book) {
		int ko = getKorCnt(book.getPublisher());
		if (book.getPublisher().length() > 15 - ko) {
			return book.getPublisher().substring(0, 15 - ko) + "...";
		} else {
			return book.getPublisher();
		}
	}

	/**
	 * 계정정보의 이름을 축약하는 메서드. 이름이 6글자가 넘어가면, 5번째 글자 이후는 ... 으로 출력된다.
	 * @param user : 이름을 축약할 계정의 정보(User 타입)
	 * @return 반각문자, 전각문자 상관없이 최대 5글자까지 출력이 가능하다.
	 * 6글자가 넘어가면 ... 으로 출력된다.
	 */
	private String userNameLength(User user) {
		if (user.getUserName().length() > 5) {
			return user.getUserName().substring(0, 5) + "...";
		} else {
			return user.getUserName();
		}
	}

	/**
	 * 생년월일의 길이를 확인하는 메서드. 생년월일은 6자리로 제한한다.
	 * @param birth : 길이를 확인할 생년월일
	 * @return 조건이 true면 생년월일 반환, false면 문구 출력
	 */
	private String birthLength(String birth) {
		if (birth.length() == 6) {
			return birth;
		} else {
			throw new RuntimeException("6자리로 입력해주세요.[ex)921024]");
		}
	}

	/**
	 * 전화번호의 길이를 확인하는 메서드. 전화번호 입력은 11자리로 제한한다.
	 * @param birth : 길이를 확인할 생년월일
	 * @return 조건이 true면 생년월일 반환, false면 문구 출력
	 */
	private String pnLength(String pn) {
		if (pn.length() == 11) {
			return pn.substring(0, 3) + "-" + pn.substring(3, 7) + "-" + pn.substring(7, 11);
		} else {
			throw new RuntimeException("11자리로 입력해주세요.[ex)01086940273]");
		}
	}

	/** @데이터_출력_관련_index_양식_메서드 */
	
	/**
	 * 도서 재고 조회 Index 양식 메서드.
	 */
	private void bookIndex() {
		System.out.printf("────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
		System.out.printf("│  도서번호  │          책제목          │         저자         │        출판사        │      ISBN     │  재고  │%n");
		System.out.printf("================================================================================================================%n");
	}
	
	/**
	 * 소장도서 대여여부 조회 Index 양식 메서드.
	 */
	private void libBookIndex() {
		System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
		System.out.printf("│  도서번호  │  소장번호  │          책제목          │         저자         │        출판사        │ 대여여부 │%n");
		System.out.printf("===============================================================================================================%n");
	}
	
	/**
	 * 도서대여이력 Index 양식 메서드.
	 */
	private void listIndex() {
		System.out.printf("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
		System.out.printf("│  대여번호  │       이름       │          책제목          │         저자         │        출판사        │   대여일   │   반납일   │ 대여여부 │%n");
		System.out.printf("===============================================================================================================================================%n");
	}

	/**
	 * 계정목록 Index 양식 메서드.
	 */
	private void userIndex() {
		System.out.printf("──────────────────────────────────────────────────────────────────%n");
		System.out.printf("│       이름       │   생년월일   │    전화번호   │     비고     │%n");
		System.out.printf("==================================================================%n");
	}
	
	/**
	 * 대여할 도서목록 Index 양식 메서드.
	 */
	private void rentIndex() {
		System.out.printf("────────────────────────────────────────────────────────────────────────────────────────%n");
		System.out.printf("│    │          책제목          │         저자         │        출판사        │  재고  │%n");
		System.out.printf("========================================================================================%n");
	}

	/**
	 * 반납할 도서목록 Index 양식 메서드.
	 */
	private void rentListIndex() {
		System.out.printf("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────%n");
		System.out.printf("│    │ 대여번호 │          책제목          │         저자         │        출판사        │   대여일   │  연체여부  │%n");
		System.out.printf("====================================================================================================================%n");
	}

	// Singleton: 기본데이터 저장
	private static LibServiceImpl libServiceImpl = new LibServiceImpl();

	private LibServiceImpl() {
	}

	public static LibServiceImpl getInstance() { // Singleton 생성
		return libServiceImpl;
	}

	// 싱글턴 변수
	private List<Book> books = new ArrayList<Book>(); // Book data
	private List<LibBook> lBooks = new ArrayList<LibBook>(); // LibBook data
	private List<User> users = new ArrayList<User>(); // User data
	private List<Rent> rents = new ArrayList<Rent>(); // Rent data

	// 싱글턴 겟터, 셋터
	public List<Book> getBooks() {
		return books;
	}

	public List<LibBook> getlBooks() {
		return lBooks;
	}

	public List<User> getUsers() {
		return users;
	}

	public List<Rent> getRents() {
		return rents;
	}

	{ // 초기화 블럭
		// Book 데이터
		books.add(new Book(101, "카이사르의 마지막 숨", "샘 킨", "해나무", "9791164051298"));
		books.get(0).setAmount(3);

		books.add(new Book(102, "주목받는 카메라연기 레슨", "안지은", "한권의책", "9791185237053")); // 제목 '카' 중복
		books.get(1).setAmount(2);

		books.add(new Book(103, "숙명", "히가시노 게이고", "창해", "9788979197662"));
		books.get(2).setAmount(1);

		books.add(new Book(104, "조선의 위기 대응 노트", "김준태", "민음사", "9788937444654"));
		books.get(3).setAmount(1);

		books.add(new Book(105, "형제", "김준태", "지식을 만드는 지식", "9788964062920")); // 저자 중복
		books.get(4).setAmount(1);

		books.add(new Book(106, "Java performance", "Scott Oaks", "Reilly", "9781492056119")); // 출판사 중복
		books.get(5).setAmount(3);

		books.add(new Book(107, "definitive guide", "Eric A. Meyer", "Reilly", "9780596527334"));
		books.get(6).setAmount(1);

		books.add(new Book(108, "Treatment of disorders", "Eric A. Youngstrom", "Guilford Press", "9781462547715")); // 해외저자
																														// 중복
		books.get(7).setAmount(2);

		books.add(new Book(109, "다크 데이터", "데이비드 핸드", "더퀘스트", "9791165217099"));
		books.get(8).setAmount(3);

		// LibBook 데이터(소장도서)
		lBooks.add(new LibBook(10001, 101)); // 카이사르의 마지막 숨
		lBooks.add(new LibBook(10002, 101)); // 카이사르의 마지막 숨
		lBooks.add(new LibBook(10003, 101)); // 카이사르의 마지막 숨
		lBooks.add(new LibBook(10004, 102)); // 주목받는 카메라연기 레슨
		lBooks.add(new LibBook(10005, 102)); // 주목받는 카메라연기 레슨
		lBooks.add(new LibBook(10006, 103)); // 숙명
		lBooks.add(new LibBook(10007, 104)); // 조선의 위기 대응 노트
		lBooks.add(new LibBook(10008, 105)); // 형제
		lBooks.add(new LibBook(10009, 106)); // Java performance
		lBooks.add(new LibBook(10010, 106)); // Java performance
		lBooks.add(new LibBook(10011, 106)); // Java performance
		lBooks.add(new LibBook(10012, 107)); // definitive guide
		lBooks.add(new LibBook(10013, 108)); // Treatment of disorders
		lBooks.add(new LibBook(10014, 108)); // Treatment of disorders
		lBooks.add(new LibBook(10015, 109)); // 다크 데이터
		lBooks.add(new LibBook(10016, 109)); // 다크 데이터
		lBooks.add(new LibBook(10017, 109)); // 다크 데이터

		// User 데이터
		users.add(new User("혁오", "981111", "010-1111-1111", 1001, "1234", true)); // 관리자
		users.add(new User("너드커넥션", "981112", "010-1111-1112", 1002, "1234", false));
		users.add(new User("카더가든", "981113", "010-1111-1113", 1003, "1234", false));
		users.add(new User("오존", "981114", "010-1111-1114", 1004, "1234", false));
		users.add(new User("설", "981115", "010-1111-1115", 1005, "1234", false));
		users.add(new User("다섯", "981116", "010-1111-1116", 1006, "1234", true));

		// Rent 데이터(도서 대여·반납)
		rents.add(new Rent(1001, users.get(0).getId(), lBooks.get(0).getId())); // 연체
		rents.get(0).setDateRent(1643718644381l);
		lBooks.get(0).setRent(true);
		rents.add(new Rent(1002, users.get(0).getId(), lBooks.get(1).getId()));
		rents.get(1).setDateRent(1642938620000l);
		rents.add(new Rent(1003, users.get(0).getId(), lBooks.get(2).getId()));
		rents.add(new Rent(1004, users.get(1).getId(), lBooks.get(3).getId()));
		rents.add(new Rent(1005, users.get(2).getId(), lBooks.get(4).getId()));
		rents.add(new Rent(1006, users.get(3).getId(), lBooks.get(5).getId()));
	}

	// EasterEgg
	public void eggBook() {
		System.out.println();
		System.out.println("    ╔═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╗   ╔═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╗   ╔═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╗   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │   ┌───────────────────────┐   │   │   ┌───────────────────────┐   │   │   ┌───────────────────────┐   │   ");
		System.out.println("    │   │   생활코딩으로        │   │   │   │   영등포에서          │   │   │   │  휴먼 휴먼 =          │   │   ");
		System.out.println("    │   │      한달에 1억 벌기  │   │   │   │     감자 키우는 꿀팁  │   │   │   │     new 휴먼교육센터  │   │   ");
		System.out.println("    │   └───────────────────────┘   │   │   └───────────────────────┘   │   │   └───────────────────────┘   │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    │                 저자: 박종민  │   │                 저자: 박종민  │   │                 저자: 박종민  │   ");
		System.out.println("    │                               │   │                               │   │                               │   ");
		System.out.println("    ╚═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╝   ╚═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╝   ╚═══*.·:·.☽✧    ✦    ✧☾.·:·.*═══╝   ");
		System.out.println();
	}

	public void eggWord() {
		int i = (int) (Math.random() * 10);
		System.out.println();

		switch (i) {
		case 0:
			System.out.println("┌───────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│나 자신에 대한 자신감을 잃으면 온 세상이 나의 적이 된다. – 랄프 왈도 에머슨│");
			System.out.println("└───────────────────────────────────────────────────────────────────────────┘");
			break;
		case 1:
			System.out
					.println("┌────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│항상 맑으면 사막이 된다. 비가 내리고 바람이 불어야만 비옥한 땅이 된다. – 스페인 속담│");
			System.out
					.println("└────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 2:
			System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│인생에서 가장 슬픈 세 가지. 할 수 있었는데, 해야 했는데, 해야만 했는데. – 루이스 E. 분│");
			System.out.println("└──────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 3:
			System.out.println("┌───────────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│같은 실수를 두려워하되 새로운 실수를 두려워하지 마라. 실수는 곧 경험이다. – 도서 ‘어떤 하루’ 中│");
			System.out.println("└───────────────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 4:
			System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
			System.out.println("│오늘은 당신의 남은 인생 중, 첫 번째 날이다. – 영화 ‘아메리칸 뷰티’ 中│");
			System.out.println("└─────────────────────────────────────────────────────────────────────┘");
			break;
		case 5:
			System.out.println("┌───────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│인생은 곱셈이다. 어떤 기회가 와도 내가 제로면 아무런 의미가 없다. – 나카무라 미츠루│");
			System.out.println("└───────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 6:
			System.out.println("┌───────────────────────────────────────────────────────┐");
			System.out.println("│별은 바라보는 자에게 빛을 준다. – 도서 ‘드래곤 라자’ 中│");
			System.out.println("└───────────────────────────────────────────────────────┘");
			break;
		case 7:
			System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│생명이 있는 한 희망이 있다. 실망을 친구로 삼을 것인가, 아니면 희망을 친구로 삼을 것인가. – J.위트│");
			System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 8:
			System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│실패란 넘어지는 것이 아니라, 넘어진 자리에 머무는 것이다. – 도서 ‘프린세스, 라 브라바!’ 中│");
			System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		case 9:
			System.out.println("┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│슬픔이 그대의 삶으로 밀려와 마음을 흔들고 소중한 것을 쓸어가 버릴 때면 그대 가슴에 대고 말하라. “이것 또한 지나가리라” – 랜터 윌슨 스미스│");
			System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
			break;
		}
		System.out.println();
	}
}