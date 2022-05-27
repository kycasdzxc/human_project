package controller.chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Chat;
import service.ChatService;

@SuppressWarnings("serial")
@WebServlet("/chat")
public class ChatController extends HttpServlet{
	private ChatService chatService = ChatService.getInstance();
	private Gson gson = new Gson();
	
	// 댓글 단일 조회
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Long cno = Long.parseLong(req.getParameter("cno"));
			String receiver = req.getParameter("receiver");
			System.out.println(cno);
			System.out.println(receiver);
			Chat chat = chatService.get(cno, receiver);
			
			resp.setContentType("application/json; charset=utf-8");
			resp.getWriter().print(gson.toJson(chat));
		}
		
	// 쪽지 보내기
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Chat chat = gson.fromJson(req.getReader().readLine(), Chat.class);
		chatService.register(chat);
		
		resp.setContentType("text/plain; charset=utf-8");
	}
}