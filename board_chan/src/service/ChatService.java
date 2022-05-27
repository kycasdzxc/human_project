package service;

import java.util.List;

import dao.ChatDao;
import domain.Chat;

public class ChatService {
	private static ChatService chatService = new ChatService();
	
	public static ChatService getInstance() {
		return chatService;
	}
	
	private ChatService() {}
	
	private ChatDao chatDao = ChatDao.getInstacne();
	
	public List<Chat> list(String sender, String receiver) {
		return chatDao.list(sender, receiver);
	}
	public void register(Chat chat) {
		chatDao.register(chat);
	}
	public Chat get(Long cno, String receiver) {
		if(chatDao.get(cno).getRDate() == null && receiver.equals(chatDao.get(cno).getReceiver())) {
			chatDao.checkChat(cno);
		}
		return chatDao.get(cno);
	}
}