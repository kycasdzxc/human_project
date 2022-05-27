package controller.Member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Criteria;
import service.MemberService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/member/profile")
public class Profile extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		System.out.println(id);
		
		Criteria criteria = new Criteria();
		if(req.getParameter("pageNum") != null) {
			criteria.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		}
		req.setAttribute("cri", criteria);
		req.setAttribute("memberInfo", memberService.get(id));
		
		req.getRequestDispatcher(Const.member("profile")).forward(req, resp);
	}
}
