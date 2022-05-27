package works.yermi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.CriteriaReply;
import works.yermi.domain.ReplyAttachVO;
import works.yermi.domain.ReplyVO;
import works.yermi.mapper.PensionMapper;
import works.yermi.mapper.ReplyAttachMapper;
import works.yermi.mapper.ReplyMapper;

@Service @AllArgsConstructor @Log4j
public class ReplyServiceImpl implements ReplyService{
	private ReplyMapper mapper;
	private ReplyAttachMapper attachMapper;
	private PensionMapper pensionMapper;
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		int result = mapper.insertSelectKey(vo);
		pensionMapper.updateReplyCnt(vo.getPensionid());
		pensionMapper.updateStarRate(vo.getPensionid());
		return result;
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		int result = mapper.update(vo);
		pensionMapper.updateStarRate(vo.getPensionid());
		return result;
	}
	
	@Override
	public int remove(Long rno) {
		pensionMapper.updateReplyCnt(mapper.read(rno).getPensionid());
		return mapper.delete(rno);
	}
	
	/**
	 * @author 김동엽
	 * @since 2022-05-10
	 * @param pensionid 펜션 아이디
	 * @return map list : 펜션에 달린 리뷰 목록
	 * @return map star : 리뷰들의 평점 및 리뷰갯수
	 */
	@Override
	public Map<String, Object> getList(Long pensionid, CriteriaReply cri) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReplyVO> replies = mapper.getListWithPaging(pensionid, cri);
		replies.forEach(r->r.setAttachs(attachMapper.findBy(r.getRno()))); // 댓글 목록 반복, 댓글 번호를 재료로 첨부파일 목록가져와서 댓글에 지정
		map.put("list", replies);
		map.put("star", mapper.readStarRate(pensionid));
		log.info("get Reply List of a pension : " + pensionid);
		return map;
	}

	@Override
	public List<ReplyVO> getListByUser(String nickName) {
		// TODO Auto-generated method stub
		return mapper.get(nickName);
	}

	// 첨부파일
	@Override
	public List<ReplyAttachVO> getAttach(Long rno) {
		// TODO Auto-generated method stub
		return attachMapper.findBy(rno);
	}

	@Override
	public int registerAttach(ReplyAttachVO vo) {
		// TODO Auto-generated method stub
		return attachMapper.insertAttach(vo);
	}
	
	@Override
	public boolean modifyAttach(ReplyVO vo) {
		// 댓글 첨부파일 일괄 삭제
		attachMapper.deleteAllAttach(vo.getRno());
		
		// 댓글 첨부파일 재등록
		vo.getAttachs().forEach(attach -> {
			attach.setRno(vo.getRno());
			attachMapper.insertAttach(attach);
		});
		
		return mapper.update(vo) > 0;
	}

	@Override
	public boolean removeAttach(String uuid) {
		// TODO Auto-generated method stub
		return attachMapper.deleteAttach(uuid) > 0;
	}

	@Override
	public boolean removeAllAttach(Long rno) {
		// TODO Auto-generated method stub
		return attachMapper.deleteAllAttach(rno) > 0;
	}

}
