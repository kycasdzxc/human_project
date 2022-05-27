package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Board;
import domain.Criteria;
import lombok.extern.log4j.Log4j;

@Log4j
public class BoardDaoTests {
	private BoardDao boardDao = BoardDao.getInstacne();
	
	@Test
	public void testExist() {
		assertNotNull(boardDao);
	}
	
	@Test
	public void testList() {
		Criteria criteria = new Criteria(1, 10, 1);
		
		boardDao.list(criteria, "").forEach(log::info);
	}
	
	@Test
	public void testGet() {
		log.info(boardDao.get(162L));
	}
	
	@Test
	public void testRegister() {
		Board board = new Board("test title", "test content", "chan", 1);
		boardDao.register(board);
	}
	
	@Test
	public void testModify() {
		Board board = new Board(163L, "modify title", "modify content", 1);
		boardDao.modify(board);
	}

	@Test
	public void testRemove() {
		boardDao.remove(163L);
		log.info(boardDao.get(162L));
	}
	
	@Test
	public void testCount() {
		Criteria criteria = new Criteria();
		criteria.setCategory(1);
		log.info(boardDao.count(criteria, "chan"));
		
		criteria.setCategory(2);
		log.info(boardDao.count(criteria, "chan"));
	}
	
	@Test
	public void testModifyNullByWriter() {
		boardDao.modifyNullByWriter("chan");
	}
}
