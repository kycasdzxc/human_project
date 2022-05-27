package works.yermi.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
import org.springframework.ui.ModelMap;
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
public class RoomControllerTests {
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
	
	@Test
	public void testList() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/room/list")
				.param("pensionid", "62735").param("lastRoomNum", "0").param("amount", "100"))
				.andReturn().getModelAndView().getModelMap();
		List<?> list = (List<?>)map.get("list");
		list.forEach(log::info);
	}
	
	@Test
	public void testAttachsList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/room/attachs")
		.param("pensionid", "62735").param("roomNum", "8698"))
		.andReturn()
		.getModelAndView();
	}
}