package controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/findMember")
public class FindMember extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		
		resp.setContentType("text/plain; charset=utf-8");

		if(id != null) {
			resp.getWriter().print(memberService.get(id) != null ? 1 : "");
		}
		if(email != null) {
			resp.getWriter().print(memberService.findBy(email) != null ? 1 : "");
		}
	}
}
