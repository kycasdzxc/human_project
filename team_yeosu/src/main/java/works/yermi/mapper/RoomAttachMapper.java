package works.yermi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import works.yermi.domain.RoomAttachVO;

public interface RoomAttachMapper {
	int insert(RoomAttachVO vo);
	
	int delete(String uuid);
	
	int deleteAll(Long roomNum);
	
	
	List<RoomAttachVO> findBy(Long roomNum);
	
}
