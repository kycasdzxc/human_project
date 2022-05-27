package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.MemberService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/index")
public class Index extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("member") != null) {
			Member member = (Member)req.getSession().getAttribute("member");
			member = memberService.get(member.getId());
			req.setAttribute("memberInfo", member);
		}
		
		req.getRequestDispatcher(Const.common("index")).forward(req, resp);
	}
}