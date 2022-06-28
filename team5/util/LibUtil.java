package util;

import java.util.Scanner;

import exception.RangeException;

public class LibUtil {
	private static Scanner scanner = new Scanner(System.in);
	
	public static String nextLine(String input) {
		System.out.print(input);
		return scanner.nextLine();		
	}
	
	public static String nextLine(String input, boolean korean, boolean num) {
		System.out.print(input);
		String str = scanner.nextLine();
		if (korean) {
			for (int i = 0 ; i < str.length() ; i++) {
				if (!(str.charAt(i) < 'ㄱ' || str.charAt(i) > 'ㅣ')) {
					throw new RuntimeException("다시 한 번 확인해주세요.");
				}
			}
		}
		if (num) {
			for (int i = 0 ; i < str.length() ; i++) {
				if (str.charAt(i) < '0' || str.charAt(i) > '9') {
					throw new RuntimeException("숫자만 입력해주세요.");
				}
			}
		}
		return str;
	}
	
	public static int nextInt(String input) {
		return Integer.parseInt(nextLine(input));
	}
	
	public static int nextInt(String input, int start, int end) {
		int result = Integer.parseInt(nextLine(input));
		if (start > result || end < result)
			throw new RangeException(start, end);
		return result;
	}
	
	public static int getKorCnt(String kor) {
		int cnt = 0;
		for (int i = 0 ; i < kor.length() ; i++) {
			if (kor.charAt(i) >= '가' && kor.charAt(i) <= '힣') {
				cnt++;
			}
		} return cnt;
	}
	
	public static String convert(String word, int size) {
		String formatter = String.format("%%%ds", size - getKorCnt(word));
		return String.format(formatter, word);
	}
	
	public static String convertLeft(String word, int size) {
		String formatter = String.format("%%-%ds", size - getKorCnt(word));
		return String.format(formatter, word);
	}
}