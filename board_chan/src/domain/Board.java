package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Board {
	private Long bno; // PK : 기본키
	private String title;
	private String content;
	private int hitCount;
	private String regDate;
	private int category;
	
	private String writer; // FK : 외래키

	private int replyCnt;
	
	// 등록작업
	public Board(String title, String content, String writer, int category) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.category = category;
	}
	
	// 수정작업
	public Board(Long bno, String title, String content, int category) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	// 조회작업
	public Board(Long bno, String title, String content, int hitCount, String regDate, int category, String writer) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.hitCount = hitCount;
		this.regDate = regDate;
		this.category = category;
		this.writer = writer;
	}
}