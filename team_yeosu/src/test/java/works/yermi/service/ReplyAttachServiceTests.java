package works.yermi.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.domain.ReplyAttachVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class ReplyAttachServiceTests {
	@Setter @Autowired
	private ReplyService replyService;
	
	@Test
	public void testExist() {
		assertNotNull(replyService);
	}
	
	@Test//39846L
	public void testGetList() {
		List<ReplyAttachVO> list = replyService.getAttach(39846L);
		list.forEach(log::info);
	}
}
