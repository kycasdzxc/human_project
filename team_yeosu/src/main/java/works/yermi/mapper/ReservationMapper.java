package works.yermi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import works.yermi.domain.ReservationVO;

public interface ReservationMapper {
	List<ReservationVO> getList(); // 전체 예약 목록조회

	List<ReservationVO> getListByUserid(String userid); // 유저아이디로 예약 목록조회

	List<ReservationVO> getListByPensionid(Long pensionid); // 펜션아이디로 예약 목록조회

	List<ReservationVO> getListCheckReservation(Long pensionid); // 펜션 이용 예정 내역
	
	List<ReservationVO> checkDate(@Param("checkin") String checkin, @Param("checkout") String checkout, @Param("roomNum") Long roomNum);
	
	ReservationVO read(Long reservationNum);
	
	int insert(ReservationVO vo); // 예약등록
	
	int updateStatus(Long reservationNum); // 예약취소
	
	int delete(Long reservationNum); // 예약 DB 삭제
	
	Integer sumPrice(Long pensionid);
	
	Integer checkReservation(Long pensionid);
	
	Integer reservationAtMonth(Long pensionid);
}
