package controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Reply;
import service.ReplyService;

@SuppressWarnings("serial")
@WebServlet("/reply")
public class ReplyController extends HttpServlet{
	private ReplyService replyService = ReplyService.getInstance();
	private Gson gson = new Gson();
	
	// 댓글 등록
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reply reply = gson.fromJson(req.getReader().readLine(), Reply.class);
		replyService.register(reply);
		
		resp.setContentType("text/plain; charset=utf-8");
	}

	// 댓글 삭제
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reply reply = gson.fromJson(req.getReader().readLine(), Reply.class);
		replyService.remove(reply.getRno());
		
		resp.setContentType("text/plain; charset=utf-8");
	}
}