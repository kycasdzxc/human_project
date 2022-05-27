package works.yermi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.PensionVO;
import works.yermi.domain.RoomAttachVO;
import works.yermi.domain.RoomVO;
import works.yermi.mapper.PensionMapper;
import works.yermi.mapper.RoomAttachMapper;
import works.yermi.mapper.RoomMapper;

@Service @AllArgsConstructor
public class RoomServiceImpl implements RoomService {
	private RoomMapper mapper;
	private RoomAttachMapper attachmapper;
	private PensionMapper pensionmapper;

	@Override
	public List<RoomVO> getList(CriteriaRoom cri,Long pensionid) {
		// TODO Auto-generated method stub
		List<RoomVO> list = mapper.getListWithPaging(cri, pensionid);
		list.forEach(room -> {
			room.setAttachs(attachmapper.findBy(room.getRoomNum()));
		});
		return list;
	}
	
	@Override
	public List<RoomVO> getCheckoutList() {
		return mapper.getCheckoutList();
	}
	
	@Override
	public int checkoutRoom(Long roomNum) {
		return mapper.checkoutRoom(roomNum);
	}
	
	@Override
	public RoomVO get(Long roomNum) {
		// TODO Auto-generated method stub
		return mapper.read(roomNum);
	}

	@Override
	public int register(RoomVO vo) {
		int result = mapper.insertSelectKey(vo);
		pensionmapper.updatePrice(vo.getPensionid());
		return result;
	}

	@Override
	public boolean modify(RoomVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo) > 0;
	}

	@Override
	public boolean remove(Long roomNum) {
		// TODO Auto-generated method stub
		return mapper.delete(roomNum)> 0;
	}

	@Override
	public List<RoomAttachVO> getAttachs(Long roomNum) {
		// TODO Auto-generated method stub
		return attachmapper.findBy(roomNum);
	}

}
