package controller.reply;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Reply;
import service.ReplyService;

@SuppressWarnings("serial")
@WebServlet("/replies")
public class ReplyList extends HttpServlet{
	private ReplyService replyService = ReplyService.getInstance();
	private Gson gson = new Gson();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		System.out.println(bno);
		List<Reply> replies = replyService.list(bno);
		
		System.out.println("doGet");
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(gson.toJson(replies));
	}
}