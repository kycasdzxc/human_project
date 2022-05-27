package controller.Member;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.Member;
import service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/secession")
public class Secession extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		memberService.remove(searchMember(req));
		resp.sendRedirect("logout");
	}
	
	private Member searchMember(HttpServletRequest req) {
		Member member = new Member();
		String saveDir = "D:\\upload";
		int size = 10 * 1024 * 1024;
		
		File currentDir = new File(saveDir);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDir);
		factory.setSizeThreshold(size);
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> items = upload.parseRequest(req);
			for(FileItem fi : items) {
				if(fi.isFormField()) {
					if(fi.getFieldName().equals("id")) {
						member.setId(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("name")) {
						member.setName(fi.getString("utf-8"));
					}
				}
				else {
					
					if(fi.getSize() == 0) {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}