package dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import domain.Attach;
import domain.Member;
import lombok.extern.log4j.Log4j;

@Log4j
public class AttachDaoTests {
	private AttachDao attachDao = AttachDao.getInstacne();
	
	@Test
	public void testExist() {
		assertNotNull(attachDao);
	}
	
	@Test
	public void testList() {
		attachDao.list().forEach(log::info);
	}
	
	@Test
	public void testGet() {
		Member member = new Member();
		member.setId("chan");
		
		Attach attach = attachDao.get(member.getId());
		
		log.info(attach.getUuid());
		log.info(attach.getOrigin());
		log.info(attach.getPath());
		log.info(attach.isImage());
		log.info(attach.getId());
	}
	
	@Test
	public void testInsert() {
		Attach attach = new Attach();
		attach.setUuid("test uuid");
		attach.setOrigin("test origin");
		attach.setPath("test path");
		attach.setImage(true);
		attach.setId("chan");
		log.info(attach);
		
		attachDao.insert(attach);
	}
	
	@Test
	public void testChangeNull() {
		attachDao.changeNull("chan");
	}
	
	@Test
	public void testRemoveNull() {
		attachDao.removeNull();
	}
}
