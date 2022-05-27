package works.yermi.service;

import java.util.List;

import works.yermi.domain.PensionAttachVO;

public interface PensionAttachService {
	List<PensionAttachVO> getList(Long pensionid);
	
	int register(PensionAttachVO vo);
	
	boolean remove(String uuid);
	
	boolean removeAll(Long pensionid);
}
