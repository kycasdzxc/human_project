package works.yermi.service;

import java.util.List;

import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.PensionVO;
import works.yermi.domain.RoomAttachVO;
import works.yermi.domain.RoomVO;

public interface RoomService {
	List<RoomVO> getList(CriteriaRoom cri, Long pensionid);
	
	List<RoomVO> getCheckoutList();
	
	
	int checkoutRoom(Long roomNum);
	
	RoomVO get(Long roomNum);
	
	int register(RoomVO vo);
	
	boolean modify(RoomVO vo);
	
	boolean remove(Long roomNum);
	
	List<RoomAttachVO> getAttachs(Long roomNum);
}
