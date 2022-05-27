package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Reply {
	private Long rno;
	private String content;
	private String regDate;
	
	private Long bno;
	private String writer;
}
