package works.yermi.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
@EnableWebSecurity
public class MemberControllerTests {
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
	public void testLogin() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/login"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}
	
	@Test
	public void testLoginPost() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/login")
				.param("username", "javaman")
				.param("password", "123"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}
	
	
	
	
	@Test
	public void testJoin() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/join"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}
	
	@Test
	public void testJoinPost() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.post("/member/join"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}

//	@Test
////	@WithMockUser(value="javaman", password="1234", authorities="ROLE_MEMBER")
//	@WithUserDetails(value="javaman")
//	public void testGetModify() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.getContext();
//	    log.info(ctx.getAuthentication());
//		ModelAndView mav = 
//				mvc.perform(
//						MockMvcRequestBuilders.get("/member/modify")
////						.with(authentication(ctx.getAuthentication()))
//						.with(httpBasic("javaman", "1234"))
//						)
//				
//				.andReturn()
//				.getModelAndView();
//	}
	
	@Test
    @WithUserDetails("javaman")
    public void testGetModify() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/member/modify"))
        .andExpect(status().is2xxSuccessful());
    }

	@Test
	public void testModify() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "1234", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/modify"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}
	
	@Test
	public void testListInquiry() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "1234", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));

		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/mypage/listInquiry"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}

	@Test
	public void testListInquiryA() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "1234", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/mypage/listInquiry")
				.param("bno", "1").param("groupno", "10")
				.param("title", "테스트제목").param("content", "테스트내용")
				.param("writer", "작성자")
				)
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}

	@Test  //@ResponseBody
	public void testIdCheck() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "1234", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/idcheck")
				.param("userId", "admin1")
				.param("nickname", "어드민 닉"))
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
	}
	
	@Test
	@WithMockUser
	public void testListReservation() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/member/mypage/listReservation"))
				.andReturn()
				.getModelAndView();
		
		log.info(mav);
	}

}