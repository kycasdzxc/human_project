package controller.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Criteria;
import domain.Member;
import service.BoardService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/board/modify")
public class Modify extends HttpServlet {
	private BoardService boardService = BoardService.getInstance();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		req.setAttribute("board", boardService.get(bno));
		Member member = (Member) req.getSession().getAttribute("member");
		
		if(member == null || !member.getName().equals(boardService.get(bno).getWriter())) {
			resp.sendRedirect(req.getContextPath() + "/board/list");
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
		
		req.setAttribute("board", boardService.get(bno));
		req.getRequestDispatcher(Const.board("modify")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String bno = req.getParameter("bno");
		
		Criteria criteria = new Criteria();
		criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
		criteria.setCategory(Integer.parseInt(req.getParameter("category")));
		
		Board board = new Board(Long.parseLong(bno), title, content, criteria.getCategory());
		boardService.modify(board);
		resp.sendRedirect("list" + criteria.getParams2());
	}
}