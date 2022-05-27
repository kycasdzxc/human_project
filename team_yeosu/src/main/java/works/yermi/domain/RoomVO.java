package works.yermi.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("room")
public class RoomVO {
	private Long roomNum;
	private Long pensionid;
	private String startTime;
	private String deadline;
	private boolean reservationStatus;
	private Long price;
	private boolean enabled;
	private String roomName;
	
	List<RoomAttachVO> attachs = new ArrayList<>();
}
