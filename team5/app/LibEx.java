package app;

import static util.LibUtil.nextInt;
import static util.LibUtil.nextLine;

import java.text.ParseException;

import service.LibService;
import service.LibServiceImpl;

public class LibEx {
	public static void main(String[] args) throws ParseException {
		LibService service = LibServiceImpl.getInstance();
		for (boolean bIndex = true; bIndex;) {
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			System.out.println("        :::::::::::        :::         ::::::::    :::     :::   ::::::::::        :::::::::      ::::::::      ::::::::      :::    ::: ");
			System.out.println("           :+:            :+:        :+:    :+:   :+:     :+:   :+:               :+:    :+:    :+:    :+:    :+:    :+:     :+:   :+:   ");
			System.out.println("          +:+            +:+        +:+    +:+   +:+     +:+   +:+               +:+    +:+    +:+    +:+    +:+    +:+     +:+  +:+     ");
			System.out.println("         +#+            +#+        +#+    +:+   +#+     +:+   +#++:++#          +#++:++#+     +#+    +:+    +#+    +:+     +#++:++       ");
			System.out.println("        +#+            +#+        +#+    +#+    +#+   +#+    +#+               +#+    +#+    +#+    +#+    +#+    +#+     +#+  +#+       ");
			System.out.println("       #+#            #+#        #+#    #+#     #+#+#+#     #+#               #+#    #+#    #+#    #+#    #+#    #+#     #+#   #+#       ");
			System.out.println("  ###########        ##########  ########        ###       ##########        #########      ########      ########      ###    ###       ");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			
			try {
				int loginID = nextInt("ID를 입력해주세요. > ");
				int login = service.logIn(loginID, nextLine("password를 입력해주세요. > "));
				
				switch (login) {
				case 1:
					for (boolean bAdmin = true; bAdmin;) {
						try {
							switch (nextInt("1.도서관리 2.계정관리 3.대여이력조회 4.로그아웃 > ", 1, 4)) {
							case 1:
			
								System.out.printf("%n도서관리입니다.%n");
			
								switch (nextInt("1.재고조회 2.도서대여 3.도서반납 4.도서검색 5.신규도서등록 6.도서정보수정 7.도서정보삭제 8.처음으로 > ", 0, 8)) {
								
								case 1: // 도서 목록 조회
									service.findBook();
									break;
								
								case 2: // 도서 대여
									service.rentBook(nextInt("ID를 입력해주세요. > "));
									break;
			
								case 3: // 도서 반납
									service.returnBook(nextInt("ID를 입력해주세요. > "));
									break;
			
								case 4: // 사서용 도서 검색
									service.searchList(service.searchBook());
									break;
			
								case 5: // 도서 정보 등록
									service.regBook();
									break;
			
								case 6: // 도서 정보 수정
									service.modifyBook();
									break;
			
								case 7: // 도서 정보 삭제
									service.removeBook();
									break;
			
								case 8:
									break;
									
								case 0:
									service.eggBook();
								}
								continue;
								
							case 2:
								System.out.printf("%n계정관리입니다.%n");
	
								switch (nextInt("1.계정목록 2.계정등록 3.계정정보수정 4.계정삭제 5.블랙리스트관리 6.관리자등록 7.처음으로 > ", 1, 7)) {
								case 1: // 계정 목록
									service.userList();
									break;
			
								case 2: // 계정 정보 등록
									service.regUser();
									break;
			
								case 3: // 계정 정보 수정
									service.modifyUser();
									break;
			
								case 4: // 계정 정보 삭제
									service.removeUser();
									break;
			
								case 5:
									service.blackList(nextInt("ID를 입력해주세요. > "));
									break;
	
								case 6:
									int adminID = nextInt("ID를 입력해주세요. > ");
									
									if (loginID == adminID) {
										System.out.println("본인 계정의 권한 회수는 불가합니다.");
										break;
									}
									service.admin(adminID);
									break;
									
									
								case 7:
									break;
								}
								continue;
			
							case 3: // 대여 이력 조회
								service.rentList(loginID);
								continue;
			
							case 4:
								service.logOut();
								break;
								
							case 0:
								service.eggWord();
								continue;
							}
							break;
						
						} catch (NumberFormatException e) {
							System.out.println("잘못 입력하셨습니다.");
						} catch (RuntimeException e) {
							System.out.println(e.getMessage());
						} 
					}
					break;
					
				case 2:
					
					for (boolean bUser = true; bUser;) {
						try {
							switch (nextInt("1.도서검색 2.도서대여 3.도서반납 4.도서대여이력 5.로그아웃 > ", 0, 5)) {
							case 1: // 도서 검색
								service.searchList(service.searchBook());
								continue;
								
							case 2: // 도서 대여
								service.rentBook(loginID);
								continue;
								
							case 3: // 도서 반납
								service.returnBook(loginID);
								continue;
								
							case 4: // 대여 이력 조회
								service.rentList(loginID);
								continue;
								
							case 5:
								service.logOut();
								break;
								
							case 0:
								service.eggWord();
								continue;
							}
						} catch (NumberFormatException e) {
							System.out.println("잘못 입력하셨습니다.");
						} catch (RuntimeException e) {
							System.out.println(e.getMessage());
						} 
						break;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못 입력하셨습니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
