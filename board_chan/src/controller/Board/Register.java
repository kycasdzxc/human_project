package controller.Board;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Criteria;
import service.BoardService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/board/register")
public class Register extends HttpServlet {
	private BoardService boardService = BoardService.getInstance();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login?link=" + req.getRequestURI() + "?" + URLEncoder.encode(req.getQueryString(), "utf-8"));
			return;
		}
		
		Criteria criteria = new Criteria();
		if(req.getParameter("pageNum") != null) {
			criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		}
		if(req.getParameter("amount") != null) {
			criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
		}
		if(req.getParameter("category") != null) {
			criteria.setCategory(Integer.parseInt(req.getParameter("category")));
		}
		req.setAttribute("cri", criteria);

		req.getRequestDispatcher(Const.board("register")).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");

		Criteria cri = new Criteria();
		cri.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		cri.setAmount(Integer.parseInt(req.getParameter("amount")));
		cri.setCategory(Integer.parseInt(req.getParameter("category")));
		System.out.println(cri);
		
		Board board = new Board(title, content, writer, cri.getCategory());
		
		boardService.register(board);
		resp.sendRedirect("list" + cri.getParams2());
	}
}