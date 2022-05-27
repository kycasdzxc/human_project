package service;

import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;

import domain.Criteria;
import domain.Member;
import lombok.extern.log4j.Log4j;

@Log4j
public class MemberServiceTests {
	private MemberService memberService = MemberService.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(memberService);
	}
	
	@Test
	public void testList() {
		Criteria criteria = new Criteria(1, 5, 0); 
		memberService.list(criteria).forEach(log::info);
	}
	
	@Test
	public void testGet() {
		Member member = memberService.get("mylove");
		log.info(member);
		log.info(member.getAttach());
	}
	
	@Test
	public void testResister() {
		Member member = new Member("test", "a1234", "테스트");
		memberService.register(member);
	}
	
	@Test
	public void testModify() {
		Member member = new Member("test", "1234", "변경된 계정");
		memberService.modify(member);
	}
	
	@Test
	public void testRemove() {
		Member member = new Member("test", "1234", "변경된 계정");
		memberService.remove(member);
	}
	
	@Test
	public void testLogin() {
		log.info(memberService.login(new Member("test", "a1234", null)));
		log.info(memberService.login(new Member("Test", "a1234", null)));
		log.info(memberService.login(new Member("TEST", "a1234", null)));
		log.info(memberService.login(new Member("  test", "a1234", null)));
		log.info(memberService.login(new Member("test", "a1234", null)));
		log.info(memberService.login(new Member("test", "A1234", null)));
		log.info(memberService.login(new Member("test", "  a1234", null)));
		log.info(memberService.login(new Member("test", "a1234  ", null)));
	}

	@Test
	public void testUpdateAuthToken() {
		Member member = memberService.get("chan");
		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		memberService.updateAuthToken(member);
		log.info(member.getAuthToken());

		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		memberService.updateAuthToken(member);
		log.info(member.getAuthToken());
	}
	
	@Test
	public void testUpdateAuth() {
		Member member = memberService.get("test");
		log.info(member.getAuth());
		
		member.setAuth("1");
		log.info(memberService.get("test").getAuth());
		
		memberService.updateAuth(member);
		log.info(memberService.get("test").getAuth());
	}
	
	@Test
	public void testFindby() {
		Member member = memberService.get("chan");
		log.info(memberService.findBy(member.getEmail()));
	}
	
	@Test
	public void testCount() {
		log.info(memberService.count(new Criteria()));
	}
	
	@Test
	public void testRemovePicture() {
		memberService.removePicture();
	}
}
