package works.yermi.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.AuthVO;
import works.yermi.domain.Criteria;
import works.yermi.domain.MemberVO;
import works.yermi.domain.PensionVO;
import works.yermi.domain.QnaVO;
import works.yermi.mapper.MemberMapper;
import works.yermi.mapper.PensionMapper;

@Service @AllArgsConstructor
@Log4j
public class MemberServiceImpl implements MemberService{
	private MemberMapper mapper;
	private PensionMapper pensionMapper;
	private PasswordEncoder encoder;
	
	
	@Override // 회원가입
	public void join(MemberVO memberVO) {
		memberVO.setPw(encoder.encode(memberVO.getPw()));
		mapper.insertMember(memberVO);
		AuthVO authVO = new AuthVO();
		authVO.setAuth("ROLE_MEMBER");
		authVO.setUserId(memberVO.getUserId());
		mapper.insertAuth(authVO);
	}

	@Override // 회원조회
	public MemberVO get(String userId) {
		return mapper.read(userId);
	}

	@Override // 회원 정보 수정
	public boolean modify(MemberVO memberVO) {
		if(memberVO.getPw() != null && !memberVO.getPw().equals("")) {
			memberVO.setPw(encoder.encode(memberVO.getPw()));
		}
		log.info(memberVO);
		boolean result = mapper.updateMember(memberVO) > 0;
		if(memberVO.getAuths() != null) {
			mapper.deleteAuth(memberVO.getUserId());
			memberVO.getAuths().forEach(auth->{
				mapper.insertAuth(auth);
			});
		}
		return result;
	}

	@Override // 회원 정보 수정 (관리자)
	public boolean memberListModify(MemberVO memberVO) {
		boolean result = mapper.updateMemberList(memberVO) > 0;
		if(memberVO.getAuths() != null) {
			mapper.deleteAuth(memberVO.getUserId());
			memberVO.getAuths().forEach(auth->{
				mapper.insertAuth(auth);
			});
		}
		return result;
	}

	@Override // 회원탈퇴
	public void remove(MemberVO memberVO) {
		mapper.deleteMember(memberVO);
		
//		boolean result = mapper.deleteMember(memberVO.getUserId()) > 0;
//		if(mapper.deleteMember(memberVO.getUserId()) == 1){
//			memberVO.setDelStatus(true);
//		} else {
//			memberVO.setDelStatus(false);
//		}
//		return result;
		
//		int result = mapper.deleteMember(memberVO.getUserId());
//		return result;
	}

	@Override // 유효성 검증
	public MemberVO hasMember(MemberVO memberVO) {
		MemberVO vo = mapper.hasMember(memberVO);
		if(memberVO.getPw()!= null && !encoder.matches(memberVO.getPw(), vo.getPw())) { // 비밀번호가 존재하고 그 값이 틀렸을 때
			return null;
		}
		if(memberVO.getUserId()!= null && 
				(memberVO.getEmail() != null || memberVO.getNickName() !=null)) { // id와 email이 입력
			return mapper.hasMemberUsingModify(memberVO);
		}
		return vo;
	}

	
	@Override // 이메일 인증
	public void mailSender(String email, String userId) throws UnsupportedEncodingException, IllegalArgumentException {
		MemberVO vo = get(userId);
		vo.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		mapper.updateAuthToken(vo);
		String host = "http://localhost:8080" + "/member/procAuth";
		String queryString = "userId=" + vo.getUserId() + "&authToken=" + encoder.encode(vo.getAuthToken());
		
		String content = String.format("    <table width='600' style='margin:0 auto'>\r\n" + 
				"        <tr>\r\n" + 
				"            <td align='center'><h1>이 이메일은 본인 확인을 위한 이메일 입니다.</h1></td>\r\n" + 
				"        </tr>\r\n" + 
				"        <tr>\r\n" + 
				"            <td><p style='color: #555555;'>이 메일의 소유자가 %s님 본인일 경우 아래의 링크를 클릭하세요.</p></td>\r\n" + 
				"        </tr>  \r\n" + 
				"        <tr>\r\n" + 
				"            <td align='center'>\r\n" + 
				"                <div style='display: inline-block; padding:8px; background-color: #222222'>\r\n" + 
				"                    <a href='%s' style='text-decoration: none; color: #dddddd;'>인증하기</a>\r\n" + 
				"                </div>\r\n" + 
				"            </td>\r\n" + 
				"        </tr>      \r\n" + 
				"    </table>", vo.getUserId(), host + "?" + queryString);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() { // 아래 ? 에 들어갈 값 > 인증메일 수신해서 확인하여 추가
				return new PasswordAuthentication("yj.ryu345@gmail.com", "ijdqqeqojxajinfn"); // password 인증이 필요한 경우 // ? 에 들어갈 값 > 사용자로부터 수집한 password Authentication
			}
		});
		String receiver = email;
		String title = "이메일 주소 인증 확인 메일입니다.";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("yj.ryu345@gmail.com", "관리자", "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			message.setSubject(title);
			message.setContent(content, "text/html;charset=utf-8");
			
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override // 메일 인증 확인
	public boolean mailConfirm(String userId, String authToken) {
		MemberVO vo = get(userId);
//		vo.setAuthEmail(encoder.matches(vo.getAuthToken(), authToken) ? "1" : "0");
		vo.setAuthEmail(encoder.matches(vo.getAuthToken(), authToken));
		if(vo.isAuthEmail()) {
			mapper.updateAuthEmail(vo);
		}
		return vo.isAuthEmail();
	}
	
	@Override // 회원 목록 조회
	public List<MemberVO> list(Criteria cri) {
		return mapper.readList(cri);
	}

	@Override // 회원 목록 총갯수
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<QnaVO> listQna(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.listQna(cri);
	}

	@Override
	public List<QnaVO> findQnaByWriter(String userId) {
		// TODO Auto-generated method stub
		return mapper.findQnaByWriter(userId);
	}

	@Override
	public Long register(QnaVO vo) {
		// TODO Auto-generated method stub
		return mapper.register(vo);
	}

	@Override
	public QnaVO findBy(Long bno) {
		// TODO Auto-generated method stub
		return mapper.findBy(bno);
	}

	@Override
	public int getTotalCountQna(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotalCountQna(cri);
	}

	@Override
	@Transactional
	public void modifyPensionAuth(PensionVO vo) {
		// 1. pension의 enabled값 변경
		pensionMapper.updateEnabled(vo);
		
		// 2. member의 auth값 변경
		List<AuthVO> auths = get(vo.getUserid()).getAuths();
		log.warn(auths);
		if(vo.isEnabled()) { // ROLE_MANAGER 추가하는 작업
			AuthVO authVO = new AuthVO();
			authVO.setUserId(vo.getUserid());
			authVO.setAuth("ROLE_MANAGER");
			mapper.insertAuth(authVO);
		}
		else { // ROLE_MANAGER 지우는 작업
			mapper.deleteAuth(vo.getUserid());
			
			auths.forEach(auth -> {
				if(!auth.getAuth().equals("ROLE_MANAGER"))
					mapper.insertAuth(auth);
			});
		}
	}
	
	
}
