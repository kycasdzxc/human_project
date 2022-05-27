package works.yermi.mapper;

import java.util.List;

import works.yermi.domain.PensionAttachVO;

public interface PensionAttachMapper {
	int insert(PensionAttachVO vo);
	
	int delete(String uuid);
	
	int deleteAll(Long pensionid);
	
	List<PensionAttachVO> findBy(Long pension);
	
}
