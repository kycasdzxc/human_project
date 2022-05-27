package works.yermi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.domain.PensionAttachVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class})
@Log4j
public class PensionAttachServiceTests {
	@Setter @Autowired
	private PensionAttachService attachService;
	
	@Test
	public void testExist() {
		assertNotNull(attachService);
	}
	
	@Test
	public void testGetList() {
		attachService.getList(6867L).forEach(log::info);
	}
	
	@Test
	public void testInsert() {
		PensionAttachVO vo = new PensionAttachVO();
		vo.setUuid(UUID.randomUUID().toString());
		vo.setOrd(0);
		vo.setPath("main/70702");
		vo.setPensionid(70702L);
		vo.setOrigin("테스트1");
		int result = attachService.register(vo);
		int exp = 1;
		assertEquals("추가", exp, result);
	}
	
	@Test
	public void testRemove() {
		boolean result = attachService.remove("50eb97ff-1f7c-4d77-b70f-f3c7017cf3d3");
		boolean exp = true;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void testRemoveAll() {
		Long pensionid = 70702L;
		boolean result = attachService.removeAll(pensionid);
		boolean exp = true;
		assertEquals("삭제", exp, result);
	}
}
