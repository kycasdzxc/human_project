package works.yermi.service;

import java.util.List;

import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.PaymentVO;
import works.yermi.domain.RoomAttachVO;
import works.yermi.domain.RoomVO;

public interface PaymentService {
	List<PaymentVO> getList(Long pensionid);

	List<PaymentVO> getListAll();
}
