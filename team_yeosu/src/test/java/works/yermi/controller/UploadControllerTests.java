package works.yermi.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

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
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;
import works.yermi.domain.PensionVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ServletConfig.class, SecurityConfig.class, RootConfig.class})
@WebAppConfiguration
public class UploadControllerTests {
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
	}
	
	@WithMockUser
	@Test
	public void testUpload() throws Exception {
		Long pensionid = 70785L;
		String uuid = UUID.randomUUID().toString();
		mvc.perform(MockMvcRequestBuilders.post("/upload")
				.param("uuid", uuid)
				.param("origin", "test.png")
				.param("path", "pension/" + pensionid)
				.param("ord", "0")
				.param("image", "true")
				.param("pensionid", "70785"))
				.andExpect(status().is2xxSuccessful());
	}
}