package works.yermi.domain;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Alias("roomattach")
public class RoomAttachVO extends AttachFileDTO {
	private Long roomNum;

	public RoomAttachVO(String uuid, String origin, String path, int ord, boolean image, Long roomNum) {
		super(uuid, origin, path, ord, image);
		this.roomNum = roomNum;
	}

	public RoomAttachVO(AttachFileDTO dto, Long roomNum) {
		this(dto.getOrigin(), dto.getUuid(), dto.getPath(),dto.getOrd(), dto.isImage(), roomNum);
	}
}
