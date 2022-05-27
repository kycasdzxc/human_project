package works.yermi.service;

import java.util.List;

import works.yermi.domain.RoomAttachVO;

public interface RoomAttachService {
	List<RoomAttachVO> getList(Long roomNum);
	
	int register(RoomAttachVO vo);
	
	boolean remove(String uuid);
	
	boolean removeAll(Long roomNum);

	
}
