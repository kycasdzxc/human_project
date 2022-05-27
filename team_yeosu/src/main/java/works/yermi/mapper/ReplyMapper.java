package works.yermi.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import works.yermi.domain.CriteriaReply;
import works.yermi.domain.ReplyAttachVO;
import works.yermi.domain.ReplyVO;

public interface ReplyMapper {
	
	List<ReplyVO> getListWithPaging(
			@Param("pensionid") Long pensionid, 
			@Param("cri") CriteriaReply cri); // 펜션에 있는 모든 댓글
	
	int insert(ReplyVO vo); // 댓글 삽입
	
	int insertSelectKey(ReplyVO vo); // 댓글 번호 즉각 반영
	
	ReplyVO read(Long rno); // 특정 댓글 읽기
	
	List<ReplyVO> get(String nickName); // 아이디사용자가 쓴 댓글 모두
	
	int delete(Long rno);
	
	int update(ReplyVO replyVO);
	
	Map<String, Object> readStarRate(Long pensionid);
	
	@Select("SELECT DISTINCT SUBSTR(PATH, 0, INSTR(PATH, '/', -1)-1) R, PENSIONID, RNO, uuid FROM TBL_REPLY_ATTACH JOIN TBL_REPLY USING(RNO) ORDER BY R")
	List<Map<String, Object>> fileTransfer();
}
