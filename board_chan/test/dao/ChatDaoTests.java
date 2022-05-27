package dao;

import lombok.extern.log4j.Log4j;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Chat;

@Log4j
public class ChatDaoTests {
	private ChatDao chatDao = ChatDao.getInstacne();
	
	@Test
	public void testExist() {
		assertNotNull(chatDao);
	}

	@Test
	public void testList() {
		chatDao.list("chan", "cham").forEach(log::info);
	}
	
	@Test
	public void testRegister() {
		Chat chat = new Chat(null, "chan", "cham", "test");
		chatDao.register(chat);
		chatDao.register(chat);
		chatDao.register(chat);
		chatDao.register(chat);
		chatDao.register(chat);
	}
	
	@Test
	public void testGet() {
		log.info(chatDao.get(1L));
	}
	
	@Test
	public void testCheckChat() {
		Long cno = 24L;
		log.info(chatDao.get(cno));
		
		chatDao.checkChat(cno);
		log.info(chatDao.get(cno));
	}
}
