package works.yermi.mapper;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class ReplyAttachMapperTests {
	@Setter @Autowired
	private ReplyAttachMapper attachMapper;
	
	
	@Test
	public void testExist() {
		assertNotNull(attachMapper);
	}
	
	@Test
	public void testGetList() {
		attachMapper.findBy(38832L).forEach(log::info);
	}
}
