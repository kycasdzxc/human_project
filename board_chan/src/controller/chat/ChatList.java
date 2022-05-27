package controller.chat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Chat;
import service.ChatService;

@SuppressWarnings("serial")
@WebServlet("/chats")
public class ChatList extends HttpServlet{
	private ChatService chatService = ChatService.getInstance();
	private Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sender = req.getParameter("sender");
		String receiver = req.getParameter("receiver");
		
		List<Chat> chats = chatService.list(sender, receiver);
		
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(gson.toJson(chats));
	}
}