package works.yermi.mapper;

import java.util.List;

import works.yermi.domain.ReplyAttachVO;

public interface ReplyAttachMapper {
	int insertAttach(ReplyAttachVO vo);
	
	int deleteAttach(String uuid);
	
	int deleteAllAttach(Long rno);
	
	List<ReplyAttachVO> findBy(Long rno);
}
