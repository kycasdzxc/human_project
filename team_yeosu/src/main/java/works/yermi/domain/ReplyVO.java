package works.yermi.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data @Alias("reply")
public class ReplyVO {
	private Long pensionid;
	private Long rno;
	
	private int starRate;
	private String nickName;
	private String regDate;
	private String title;
	private String content;
	
	List<ReplyAttachVO> attachs = new ArrayList<>();
}
