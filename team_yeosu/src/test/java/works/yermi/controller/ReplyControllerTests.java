package works.yermi.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServletConfig.class, SecurityConfig.class, RootConfig.class})
@WebAppConfiguration
@Log4j
public class ReplyControllerTests {
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExists() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	@Test
	public void testList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("reply/pages")
		.param("pensionid", "6867"))
		.andReturn()
		.getModelAndView();
	}
	
	@Test
	public void testDetail() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("reply")
		.param("reply", "40037"))
		.andReturn()
		.getModelAndView();
	}
	
	@Test
	public void testAttachsList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("reply/attachs")
		.param("rno", "39433"))
		.andReturn()
		.getModelAndView();
	}
	
}