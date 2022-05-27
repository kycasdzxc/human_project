package works.yermi.service;

import java.util.List;

import works.yermi.domain.ReservationVO;

public interface ReservationService {
	List<ReservationVO> getList(); // 전체 예약 목록조회

	List<ReservationVO> getListByUserid(String userid); // 유저아이디로 예약 목록조회

	List<ReservationVO> getListByPensionid(Long pensionid); // 펜션아이디로 예약 목록조회
	
	List<ReservationVO> getListCheckReservation(Long pensionid); // 펜션 이용 예정 내역
	
	boolean reserve(ReservationVO vo); // 예약등록
	
	boolean cancelReservation(Long reservationNum); // 예약취소
	
	boolean delete(Long reservationNum); // 예약 DB 삭제
	
	Integer sumPrice(Long pensionid);
	
	Integer checkReservation(Long pensionid);
	
	Integer reservationAtMonth(Long pensionid);
}
