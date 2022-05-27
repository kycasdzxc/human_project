package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import oracle.security.o5logon.d;

@ToString(callSuper=true)
@AllArgsConstructor 
@NoArgsConstructor
@Getter 
@Setter
@Alias("replyAttach")
public class ReplyAttachVO extends AttachFileDTO{
	private Long rno;
	
	public ReplyAttachVO(String uuid, String origin, String path, int ord, boolean image, Long rno) {
		super(uuid, origin, path, ord, image);
		this.rno = rno;
	}
	
	public ReplyAttachVO(AttachFileDTO dto, Long rno) {
		this(dto.getUuid(), dto.getOrigin(), dto.getPath(), dto.getOrd(), dto.isImage(), rno);
	}
}
