package works.yermi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.PaymentVO;
import works.yermi.domain.RoomAttachVO;
import works.yermi.domain.RoomVO;
import works.yermi.mapper.PaymentMapper;
import works.yermi.mapper.RoomAttachMapper;
import works.yermi.mapper.RoomMapper;

@Service @AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	private PaymentMapper mapper;

	@Override
	public List<PaymentVO> getList(Long pensionid) {
		return mapper.getList(pensionid);
	}

	@Override
	public List<PaymentVO> getListAll() {
		return mapper.getListAll();
	}
	
	
}
