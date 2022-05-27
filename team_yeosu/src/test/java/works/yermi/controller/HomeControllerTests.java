package works.yermi.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServletConfig.class, SecurityConfig.class, RootConfig.class})
@WebAppConfiguration
public class HomeControllerTests {
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;
	
	@Before
	public void Setup(){
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	public void testExist() {
		assertNotNull(ctx);
		
		assertNotNull(mvc);
	}
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	public void testIndexGetList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/index"))
		.andReturn()
		.getModelAndView();
		
	}
	
}