package domain;

/**
 * 도서관에 등록된 계정 데이터.
 * 담당 : 김동엽
 */

public class User {
	
	// 변수
	private String userName; 	// 이름
	private String userBirth; 	// 생년월일
	private String userPN; 		// 전화번호
	
	private int id;		 		// 아이디 : User의 기본키
	private String pw; 			// 비밀번호
	
	private boolean admin; 		// 관리자 여부[true = 관리자]
	private boolean blacklist;	// 블랙리스트 여부[true = 블랙리스트]
	
	// 관리자와 블랙리스트는 중첩될 수 없다.
	
	// 생성자
	public User() {} // 기본 생성자
	
	public User(String userName, String userBirth, String userPN, int id, String pw, boolean admin) {
		super();
		this.userName = userName;
		this.userBirth = userBirth;
		this.userPN = userPN;
		this.id = id;
		this.pw = pw;
		this.admin = admin;
	}

	// 겟터, 셋터 : 데이터 캡슐화
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserPN() {
		return userPN;
	}

	public void setUserPN(String userPN) {
		this.userPN = userPN;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isBlacklist() {
		return blacklist;
	}

	public void setBlacklist(boolean blacklist) {
		this.blacklist = blacklist;
	}
}