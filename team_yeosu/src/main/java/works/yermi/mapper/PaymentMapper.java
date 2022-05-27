package works.yermi.mapper;

import java.util.List;

import works.yermi.domain.PaymentVO;

public interface PaymentMapper {
	List<PaymentVO> getList(Long pensionid);

	List<PaymentVO> getListAll();
	
	int insert(PaymentVO vo);

	int updateStatus(Long paymentNum);
	
}
