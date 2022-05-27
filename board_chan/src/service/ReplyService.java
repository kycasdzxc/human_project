package service;

import java.util.List;

import dao.ReplyDao;
import domain.Reply;

public class ReplyService {
	private static ReplyService replyService = new ReplyService();
	
	public static ReplyService getInstance() {
		return replyService;
	}
	
	private ReplyService() {}
	
	private ReplyDao replyDao = ReplyDao.getInstacne();
	
	// 댓글 목록
	public List<Reply> list(Long bno) {
		return replyDao.list(bno);
	}
	// 댓글 작성
	public void register(Reply reply) {
		replyDao.register(reply);
	}
	// 댓글 삭제
	public void remove(Long rno) {		
		replyDao.remove(rno);
	}
}