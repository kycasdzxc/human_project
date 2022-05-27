package dao;

import lombok.extern.log4j.Log4j;

import static org.junit.Assert.assertNotNull;

import java.util.Random;

import org.junit.Test;

import domain.Criteria;
import domain.Member;

@Log4j
public class MemberDaoTests {
	private MemberDao memberDao = MemberDao.getInstance();
	
	@Test
	public void testExist() {
		assertNotNull(memberDao);
	}

	@Test
	public void testList() {
		Criteria criteria = new Criteria(2, 5, 0);
		memberDao.list(criteria).forEach(log::info);
	}
	
	@Test
	public void testGet() {
		log.info(memberDao.get("chan"));
	}
	
	@Test
	public void testResister() {
		Member member = new Member("test", "1234", "테스트");
		memberDao.register(member);
	}
	
	@Test
	public void testModify() {
		Member member = new Member("test", "1234", "변경된 계정");
		memberDao.modify(member);
	}
	
	@Test
	public void testRemove() {
		Member member = new Member("test", "1234", "변경된 계정");
		memberDao.remove(member.getId());
	}
	
	@Test
	public void testLogin() {
		log.info(memberDao.login("chan", "1234"));
	}
	
	@Test
	public void testUpdateAuthToken() {
		Member member = memberDao.get("chan");
		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		memberDao.updateAuthToken(member);
		log.info(member.getAuthToken());

		member.setAuthToken(String.format("%08d", new Random().nextInt(100_000_000)));
		memberDao.updateAuthToken(member);
		log.info(member.getAuthToken());
	}
	
	@Test
	public void testUpdateAuth() {
		Member member = memberDao.get("test");
		log.info(member.getAuth());
		
		member.setAuth("1");
		log.info(memberDao.get("test").getAuth());
		
		memberDao.updateAuth(member);
		log.info(memberDao.get("test").getAuth());
	}
	
	@Test
	public void testFindby() {
		Member member = memberDao.get("chan");
		log.info(memberDao.findBy(member.getEmail()));
	}
	
	@Test
	public void testCount() {
		log.info(memberDao.count(new Criteria()));
	}
}
