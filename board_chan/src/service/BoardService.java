package service;

import java.util.List;

import dao.BoardDao;
import dao.ReplyDao;
import domain.Board;
import domain.Criteria;

public class BoardService {
	private static BoardService boardService = new BoardService();
	
	public static BoardService getInstance() {
		return boardService;
	}
	
	private BoardService() {}
	
	private BoardDao boardDao = BoardDao.getInstacne();
	private ReplyDao replyDao = ReplyDao.getInstacne();
	
	// 글 목록
	public List<Board> list(Criteria criteria, String keyword) {
		List<Board> list = boardDao.list(criteria, keyword);
		return list;
	}
	// 글 상세
	public Board get(Long bno) {
		Board board = boardDao.get(bno);
		return board;
	}
	// 글 작성
	public void register(Board board) {
		boardDao.register(board);
	}
	// 글 수정
	public void modify(Board board) {
		boardDao.modify(board);
	}
	// 글 삭제
	public void remove(Long bno) {
		replyDao.removeAll(bno); // 댓글 삭제
		boardDao.remove(bno);	 // 게시글 삭제
	}

	public int count(Criteria cri, String keyword) {
		return boardDao.count(cri, keyword);
	}
}