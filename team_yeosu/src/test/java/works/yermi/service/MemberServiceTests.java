package works.yermi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.AuthVO;
import works.yermi.domain.MemberVO;
import works.yermi.mapper.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberServiceTests {
	@Setter @Autowired
	private PasswordEncoder encoder;
	@Setter @Autowired 
	private MemberMapper mapper;
	
	@Test
	public void testsJoin() {
		MemberVO vo = new MemberVO();
		vo.setUserId("id11");
		vo.setPw(encoder.encode("1234"));
		vo.setName("관리자");
		vo.setNickName("관리자닉");
		mapper.insertMember(vo);
		
		log.info(vo);
		
		AuthVO authVO = new AuthVO();
		authVO.setAuth("ROLE_MEMBER");
		authVO.setUserId(vo.getUserId());
		mapper.insertAuth(authVO);
		
		log.info(authVO);
	}
}
