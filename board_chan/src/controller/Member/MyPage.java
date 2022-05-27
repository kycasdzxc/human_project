package controller.Member;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.Attach;
import domain.Member;
import net.coobird.thumbnailator.Thumbnails;
import service.MemberService;
import utils.Const;

@SuppressWarnings("serial")
@WebServlet("/member/myPage")
public class MyPage extends HttpServlet{
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("member") == null) {
			resp.sendRedirect(req.getContextPath() + "/member/login?link=" + req.getRequestURI() + "?" +
						URLEncoder.encode(req.getQueryString() == null ? "" : req.getQueryString(), "utf-8"));
			return;
		}
		Member member = (Member)req.getSession().getAttribute("member");
		member = memberService.get(member.getId());
		req.setAttribute("memberInfo", member);
		
		req.getRequestDispatcher(Const.member("mypage")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = utils.Util.getParam(req, Member.class);

		member = upload(req);	
		
		System.out.println(member);
		
		memberService.modify(member);
		
		if(member.getAttach() == null || member.getAttach().isImage()) {
			req.setAttribute("msg", "회원 정보가 정상적으로 수정되었습니다.");
			req.getRequestDispatcher(Const.common("msg")).forward(req, resp);			
		}
		else {
			req.setAttribute("msg", "이미지 파일을 등록해주세요.");
			req.getRequestDispatcher(Const.common("msg")).forward(req, resp);
		}
	}
	
	private Member upload(HttpServletRequest req) {
		Member member = new Member();
		String saveDir = "C:\\upload";
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
					if(fi.getFieldName().equals("pw")) {
						member.setPw(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("name")) {
						member.setName(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("si")) {
						member.setSi(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("sgg")) {
						member.setSgg(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("emd")) {
						member.setEmd(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("roadAddr")) {
						member.setRoadAddr(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("addrDetail")) {
						member.setAddrDetail(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("zipNo")) {
						member.setZipNo(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("roadFullAddr")) {
						member.setRoadFullAddr(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("jibunAddr")) {
						member.setJibunAddr(fi.getString("utf-8"));
					}
					if(fi.getFieldName().equals("speak")) {
						member.setSpeak(fi.getString("utf-8"));
					}
				}
				else {
					
					if(fi.getSize() == 0) {
						continue;
					}
				
					String origin = fi.getName();
					int idxDot = origin.lastIndexOf(".");
					String ext = "";
					
					if(idxDot != -1) {
						ext = origin.substring(idxDot);
					}
					
					UUID uuid = UUID.randomUUID();
					String name = uuid + ext; // 물리적 uuid
					
					File upPath = new File(currentDir + "\\" + getTodayStr());
					if(!upPath.exists()) {
						upPath.mkdirs();
					}
					fi.write(new File(upPath, name));
					
					Attach attach = new Attach(name, origin, getTodayStr());
					member.setAttach(attach);
					procImageType(attach, upPath, name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	private String getTodayStr() {
		return new SimpleDateFormat("yyyy/MM/dd").format(System.currentTimeMillis());
	}
	
	private void procImageType(Attach attach, File upPath, String name) {
		File file = new File(upPath, name);
		
		try {
			Thumbnails
				.of(file)
				.forceSize(210, 210)
				.toFile(new File(upPath, "s_" + name));
			attach.setImage(true);
		} catch (IOException ignore) { };
	}
}