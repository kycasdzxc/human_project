package works.yermi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.PensionVO;
import works.yermi.domain.RoomVO;

public interface RoomMapper {
	public List<RoomVO> getList(Long pensionid);
	
	public List<RoomVO> getCheckoutList();
	
	public List<RoomVO> getListWithPaging(@Param("cri") CriteriaRoom cri,@Param("pensionid") Long pensionid);
	
	public RoomVO read(Long roomNum);
	
	public int insert(RoomVO vo);
	
	public int insertSelectKey(RoomVO vo);
	
	public int update(RoomVO vo);
	
	public int delete(Long roomNum);
	
	public int reserveRoom(@Param("roomNum") Long roomNum, @Param("startTime") String startTime, @Param("deadLine") String deadLine);
	
	public int checkoutRoom(@Param("roomNum") Long roomNum);
}
