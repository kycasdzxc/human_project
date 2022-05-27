package controller.Member;

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
@WebServlet("/member/login")
public class Login extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Const.member("login")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String link = req.getParameter("link");
		String idInfo = req.getParameter("info");
		
		Member member = memberService.login(new Member(id, pw, null));
		System.out.println(member);
		if(member == null) {
			req.setAttribute("msg", "아이디 또는 비밀번호를 확인해주세요.");
			req.setAttribute("href", req.getRequestURI() + (link == null ? "" : "?link=" + link));
			req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
		}
		else {
			req.getSession().setAttribute("member", member);
			
			if(idInfo == null) {
				link = link == null ? req.getContextPath() + "/" : link;
				
				req.setAttribute("msg", "어서오세요, " + member.getName() + " 님!");
				req.setAttribute("href", link);
				req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
			}
			else {
				req.setAttribute("msg", "어서오세요, " + member.getName() + " 님!");
				req.setAttribute("href", req.getContextPath() + "/member/profile?id=" + idInfo);
				req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
			}
		}
	}
}
