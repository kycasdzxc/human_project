package works.yermi.domain;


import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true)
@NoArgsConstructor
@Getter
@Setter
@Alias("pension_attach")
public class PensionAttachVO extends AttachFileDTO{
	private Long pensionid;

	public PensionAttachVO(String uuid, String origin, String path, int ord, boolean image, Long pensionid) {
		super(uuid, origin, path, ord, image);
		this.pensionid = pensionid;
	}
	
	public PensionAttachVO(AttachFileDTO dto, Long pensionid) {
		this(dto.getUuid(), dto.getOrigin(), dto.getPath(), dto.getOrd(), dto.isImage(), pensionid);
	}
}
