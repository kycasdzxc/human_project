package works.yermi.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("qna")
public class QnaVO {
	private Long bno;
	private Long groupno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private boolean reply;
}
