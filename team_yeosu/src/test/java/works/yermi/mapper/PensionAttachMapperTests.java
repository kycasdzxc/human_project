package works.yermi.mapper;

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
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class PensionAttachMapperTests {
	@Setter @Autowired
	private PensionAttachMapper attachMapper;
	
	@Test
	public void testExist() {
		assertNotNull(attachMapper);
	}
	
	@Test
	public void testGetList() {
		attachMapper.findBy(70702L).forEach(log::info);
	}
	
	@Test
	public void testInsert() {
		PensionAttachVO vo = new PensionAttachVO();
		vo.setUuid(UUID.randomUUID().toString());
		vo.setOrd(0);
		vo.setPath("main/70702");
		vo.setPensionid(70702L);
		vo.setOrigin("테스트3");
		int result = attachMapper.insert(vo);
		int exp = 1;
		assertEquals("추가", exp, result);
	}
	
	@Test
	public void testDelete() {
		int result = attachMapper.delete("83294106-abb3-4414-85cf-6709f865f60b");
		int exp = 1;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void testDeleteAll() {
		Long pensionid = 70702L;
		int result = attachMapper.deleteAll(pensionid);
		int exp = attachMapper.findBy(pensionid).size();
		assertEquals("삭제", exp, result);
	}
	
	
}
