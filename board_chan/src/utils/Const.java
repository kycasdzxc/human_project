package utils;

public class Const {
	public static final String JSP = "/WEB-INF/jsp/";
	public static final String JSP_COMMON = JSP + "common/";
	public static final String JSP_BOARD = JSP + "board/";
	public static final String JSP_MEMBER = JSP + "member/";
	public static final String JSP_EXT = ".jsp";
	
	public static String common(String file) {
		return JSP_COMMON + file + JSP_EXT;
	}
	public static String board(String file) {
		return JSP_BOARD + file + JSP_EXT;
	}
	public static String member(String file) {
		return JSP_MEMBER + file + JSP_EXT;
	}
}