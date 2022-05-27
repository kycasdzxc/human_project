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
@WebServlet("/member/join")
public class Join extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Const.member("join")).forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = utils.Util.getParam(req, Member.class);
		memberService.register(member);
		
		String ctx = req.getContextPath();
		
		req.setAttribute("msg", "회원가입이 완료되었습니다.");
		req.setAttribute("href", ctx.length() == 0 ? "/" : ctx);
		req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
	}
}
