package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Chat {
	private Long cno;
	private String sender;
	private String receiver;
	private String content;
	private String sDate;
	private String rDate;
	
	public Chat(Long cno, String sender, String receiver, String content) {
		this.cno = cno;
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
}
