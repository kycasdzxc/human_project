package works.yermi.service;

import java.util.List;

import works.yermi.domain.RoomAttachVO;
import works.yermi.mapper.RoomAttachMapper;

public class RoomAttachServiceImpl implements RoomAttachService{
	private RoomAttachMapper mapper;

	@Override
	public List<RoomAttachVO> getList(Long roomNum) {
		// TODO Auto-generated method stub
		return mapper.findBy(roomNum);
	}

	@Override
	public int register(RoomAttachVO vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public boolean remove(String uuid) {
		// TODO Auto-generated method stub
		return mapper.delete(uuid) > 0;
	}

	@Override
	public boolean removeAll(Long roomNum) {
		// TODO Auto-generated method stub
		return mapper.deleteAll(roomNum)>0;
	}
	

}
