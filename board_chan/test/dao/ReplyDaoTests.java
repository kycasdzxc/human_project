package dao;

import lombok.extern.log4j.Log4j;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Reply;

@Log4j
public class ReplyDaoTests {
	private ReplyDao replyDao = ReplyDao.getInstacne();
	
	@Test
	public void testExist() {
		assertNotNull(replyDao);
	}

	@Test
	public void testList() {
		replyDao.list(162L).forEach(log::info);
	}
	
	@Test
	public void testRegister() {
		Reply reply = new Reply(null, "test", null, 162L, "test");
		replyDao.register(reply);
	}
	
	@Test
	public void testRemove() {
		Long rno = 46L;
		replyDao.remove(rno);
	}
	
	@Test
	public void testRemoveAll() {
		Long bno = 162L;
		replyDao.removeAll(bno);
	}
	
	@Test
	public void testModifyNullByWriter() {
		replyDao.modifyByWriter("김예찬");
	}
}
