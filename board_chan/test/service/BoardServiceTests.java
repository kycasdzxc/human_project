package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Board;
import domain.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardServiceTests {
	private BoardService boardService = BoardService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(boardService);
	}
	
	@Test
	public void testList() {
		Criteria criteria = new Criteria(1, 10, 1);
		
		boardService.list(criteria, "제").forEach(log::info);
	}
	
	@Test
	public void testGet() {
		log.info(boardService.get(181L));
	}
	
	@Test
	public void testRegister() {
		Board board = new Board("술래잡기 대회다구리!", "술래잡기 대회다구리!", "너굴", 1);
		boardService.register(board);
		board = new Board("별똥별이 많이 떨어지는 날이다구리!", "별똥별이 많이 떨어지는 날이다구리!", "너굴", 1);
		boardService.register(board);
		board = new Board("초롱아귀를 두 배에 매입한다구리!", "초롱아귀를 두 배에 매입한다구리!", "너굴", 1);
		boardService.register(board);
		board = new Board("새로운 주민이 이사올 예정이다구리!", "새로운 주민이 이사올 예정이다구리!", "너굴", 1);
		boardService.register(board);
	}
	
	@Test
	public void testModify() {
		Board board = new Board(181L, "modify title", "modify content", 1);
		boardService.modify(board);
		log.info(boardService.get(181L));
	}

	@Test
	public void testRemove() {
		boardService.remove(181L);
	}
	
	@Test
	public void testCount() {
		Criteria criteria = new Criteria();
		criteria.setCategory(1);
		log.info(boardService.count(criteria, "chan"));
		
		criteria.setCategory(2);
		log.info(boardService.count(criteria, "chan"));
	}
}
