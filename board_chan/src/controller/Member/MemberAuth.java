package controller.Member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import domain.Member;
import service.MemberService;

@SuppressWarnings("serial")
@WebServlet("/member/memberAuth")
public class MemberAuth extends HttpServlet {
	private MemberService memberService = MemberService.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 처리
		String id = req.getParameter("id");
//		String email = req.getParameter("email");
		
		// 암호화 처리, 난수 생성
		Member member = memberService.get(id);
		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		
		// 지정된 토큰값 처리
		memberService.updateAuthToken(member);
		String host = "http://servlet.yermi.works/member/ProcAuth";
		String queryString = "id=" + member.getId() + "&authToken=" + URLEncoder.encode(BCrypt.hashpw(member.getAuthToken(), BCrypt.gensalt()), "utf-8");
		
		String content = String.format("<table width='600' style='margin:0 auto'>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align='center'><h1>이 이메일은 본인 확인을 위한 이메일입니다.</h1></td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align='center'><p style='color: #555555;'>이 메일의 소유자가 %s님 본인일 경우, 아래의 링크를 클릭하세요.</p></td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align='center'>\r\n" + 
				"                <div style='display: inline-block; padding:8px; background-color: #222222;'>\r\n" + 
				"                    <a href='%s' style='text-decoration: none; color: #dddddd;'>인증하기</a>\r\n" + 
				"                </div>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>\r\n" + 
				"    </table>", member.getId(), host + "?" + queryString);
		
		// 메일 발송
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("yermi0001@gmail.com", "ntkkbyswowynihgg");
			}
		});
		
		String receiver = member.getEmail();
		String title = "이메일 주소 인증 확인 메일입니다.";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("kycasdzxc@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done!");
		resp.setContentType("text/plain");
		resp.getWriter().print("success");
	}
}
