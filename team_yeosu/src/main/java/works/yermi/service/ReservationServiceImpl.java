package works.yermi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import works.yermi.domain.PaymentVO;
import works.yermi.domain.ReservationVO;
import works.yermi.mapper.PaymentMapper;
import works.yermi.mapper.ReservationMapper;
import works.yermi.mapper.RoomMapper;

@Service @AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{
	ReservationMapper mapper;
	PaymentMapper paymentMapper;
	RoomMapper roomMapper;
	
	@Override
	public List<ReservationVO> getList() {
		return mapper.getList();
	}

	@Override
	public List<ReservationVO> getListByUserid(String userid) {
		return mapper.getListByUserid(userid);
	}

	@Override
	public List<ReservationVO> getListByPensionid(Long pensionid) {
		return mapper.getListByPensionid(pensionid);
	}
	
	@Override
	public List<ReservationVO> getListCheckReservation(Long pensionid) {
		return mapper.getListCheckReservation(pensionid);
	}
	
	
	@Override
	@Transactional
	public boolean reserve(ReservationVO vo) {
		int result = 0;
		int check = mapper.checkDate(vo.getCheckin(), vo.getCheckout(), vo.getRoomNum()).size();

		if(check == 0) {
			result = mapper.insert(vo);
			PaymentVO payment = new PaymentVO(vo.getReservationNum(), vo.getPaymentPrice(), null, true, vo.getUserid(), vo.getPensionid(), vo.getRoomNum());
			paymentMapper.insert(payment);
			roomMapper.reserveRoom(vo.getRoomNum(), vo.getCheckin(), vo.getCheckout());
		}
		return result > 0;
	}

	@Override
	@Transactional
	public boolean cancelReservation(Long reservationNum) {
		roomMapper.checkoutRoom(mapper.read(reservationNum).getRoomNum());
		paymentMapper.updateStatus(reservationNum);
		boolean result = mapper.updateStatus(reservationNum) > 0;
		return result;
	}

	@Override
	public boolean delete(Long reservationNum) {
		return mapper.delete(reservationNum) > 0;
	}

	@Override
	public Integer sumPrice(Long pensionid) {
		return mapper.sumPrice(pensionid);
	}

	@Override
	public Integer checkReservation(Long pensionid) {
		return mapper.checkReservation(pensionid);
	}

	@Override
	public Integer reservationAtMonth(Long pensionid) {
		return mapper.reservationAtMonth(pensionid);
	}

}
