
package works.yermi.service;

import java.util.List;
import java.util.Map;

import works.yermi.domain.CriteriaReply;
import works.yermi.domain.ReplyAttachVO;
import works.yermi.domain.ReplyVO;

public interface ReplyService {
	int register(ReplyVO vo);
	
	ReplyVO get(Long rno);
	
	int modify(ReplyVO vo);
	
	int remove(Long rno);
	
	Map<String, Object> getList(Long pensionid, CriteriaReply cri);
	
	List<ReplyVO> getListByUser(String nickName);
	
	// 첨부파일
	List<ReplyAttachVO> getAttach(Long rno);
	
	int registerAttach(ReplyAttachVO replyAttachVO);
	
	boolean modifyAttach(ReplyVO vo);
	
	boolean removeAttach(String uuid);
	
	boolean removeAllAttach(Long rno);
	
}
