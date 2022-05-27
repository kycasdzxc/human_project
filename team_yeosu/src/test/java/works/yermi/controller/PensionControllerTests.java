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
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
public class PensionControllerTests {
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;
	
	
	@Before
	public void Setup(){
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExists() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	public void testList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/pension/list"))
		.andReturn()
		.getModelAndView();
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	public void testDetail() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/pension/detail")
		.param("pensionid", "6867")
		.param("startDate", "2022/05/23")
		.param("endDate", "2022/05/27"))
		.andReturn()
		.getModelAndView();
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	public void testAttachsList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/pension/attachs")
		.param("pensionid", "6867"))
		.andReturn()
		.getModelAndView();
	}
}