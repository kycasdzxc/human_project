package controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Criteria;
import domain.PageDto;
import service.MemberService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberList extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = new Criteria();
		if(req.getParameter("pageNum") != null) {
			criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		}
		if(req.getParameter("amount") != null) {
			criteria.setAmount(Integer.parseInt(req.getParameter("amount")));
		}
		req.setAttribute("page", new PageDto(memberService.count(criteria), criteria));
		System.out.println(criteria);
		req.setAttribute("members", memberService.list(criteria));
		
		req.getRequestDispatcher(Const.member("gallery")).forward(req, resp);
	}
}