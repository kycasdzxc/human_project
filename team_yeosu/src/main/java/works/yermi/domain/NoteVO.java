package works.yermi.domain;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("note")
public class NoteVO {
	private int noteNum;
	private String sender;
	private String receiver;
	private Date timeSpent;
	private Date timeReceived;
	private char adminAuth;
	
}
