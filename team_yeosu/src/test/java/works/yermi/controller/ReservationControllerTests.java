package works.yermi.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.zaxxer.hikari.HikariConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServletConfig.class, SecurityConfig.class, RootConfig.class})
@WebAppConfiguration
@Log4j
public class ReservationControllerTests {
	@Autowired @Setter
	private WebApplicationContext ctx;
	
	private MockMvc mvc;
	
	@Autowired @Setter
	private HikariConfig hikariConfig; 
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	@WithMockUser
	public void testListReserve() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/reservation/reserve")
			.param("pensionid", "6867").param("roomNum", "7573")
			.param("date1", "2022/06/02").param("date2", "2022/06/03"))
			.andReturn()
			.getModelAndView();
	}
	
}