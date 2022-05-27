package service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Chat;
import lombok.extern.log4j.Log4j;

@Log4j
public class ChatServiceTests {
	private ChatService chatService = ChatService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(chatService);
	}
	
	@Test
	public void testList() {
		chatService.list("빙수", "김예찬").forEach(log::info);
	}
	
	@Test
	public void testRegister() {
		String name = "김예찬";
		Chat chat = new Chat(null, "햄까스", name, "날씨가 너무 좋다~ 딩동댕");
		chatService.register(chat);
		chat = new Chat(null, name, "애플", "다들 행복한 하루 되세요~ 우왕");
		chatService.register(chat);
		chat = new Chat(null, "미첼", name, "으아앗!! 요리학원에 늦어버렸다.. 그쵸");
		chatService.register(chat);
	}
	
	@Test
	public void testGet() {
		log.info(chatService.get(5L, "수신자453"));
	}
}
