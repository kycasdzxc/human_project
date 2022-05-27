package works.yermi.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.config.ServletConfig;
import works.yermi.domain.PensionVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class, SecurityConfig.class, ServletConfig.class})
@WebAppConfiguration
@Log4j
@EnableWebSecurity
public class AdminControllerTests {
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
	public void testExists() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	@Test
	public void testmemberList() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/admin/memberList")
					.param("pageNum", "1").param("amount", "10")
					.param("total", "20").param("cri", "10")
					)
				.andReturn()
				.getModelAndView();
		log.info("mav :: " + mav);
		log.info("mav.getModel() ::" +mav.getModel());
		log.info("mav.getView() ::" + mav.getView());
		log.info("mav.getViewName() ::" + mav.getViewName());
	}

	@Test
	public void testmemberListModify() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/admin/memberList")
				.param("pw", "1234").param("email", "test@test.com")
				.param("phone", "010").param("phone2", "1234").param("phone3", "5678")
				.param("point", "1000").param("roadAddr", "서울특별시 영등포구 영중로8길 6").param("addrDetail", "휴먼교육센터")
				.param("zipNo", "07302").param("jibunAddr", "서울특별시 영등포구 영등포동3가 8-1").param("name", "홍길동")
				.param("nickName", "홍길동님").param("userId", "admin1")
				)
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
		log.info("mav :: " + mav);
		log.info("mav.getModel() ::" +mav.getModel());
		log.info("mav.getView() ::" + mav.getView());
		log.info("mav.getViewName() ::" + mav.getViewName());
	}
	
	@Test
	public void testlistTotalCountQna() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/admin/listInquiry"))
//				.param("pageNum", "1").param("amount", "10")
//				.param("total", "20").param("cri", "10")
//				)
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
		log.info("mav :: " + mav);
		log.info("mav.getModel() ::" +mav.getModel());
		log.info("mav.getView() ::" + mav.getView());
		log.info("mav.getViewName() ::" + mav.getViewName());
	}
	
	@Test
	public void testQnaRegister() throws Exception {
//		SecurityContext ctx = SecurityContextHolder.createEmptyContext();
//		SecurityContextHolder.setContext(ctx);
//		ctx.setAuthentication(new UsernamePasswordAuthenticationToken("admin1", "", Arrays.asList(new SimpleGrantedAuthority("ROLE_MEMBER"), new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ADMIN"))));
		
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/admin/listInquiry")
				.param("pageNum", "1").param("amount", "10")
				.param("total", "20").param("cri", "10")
				.param("bno", "1").param("groupno", "10")
				.param("title", "테스트제목").param("content", "테스트내용")
				.param("writer", "작성자")
				)
				.andExpect(status().is2xxSuccessful())
				.andReturn()
				.getModelAndView();
		log.info("mav :: " + mav);
		log.info("mav.getModel() ::" +mav.getModel());
		log.info("mav.getView() ::" + mav.getView());
		log.info("mav.getViewName() ::" + mav.getViewName());
	}
	/**
	 *  @author 이대석
	 * 	
	 */
	@WithMockUser
	@Test
	public void testPensionList() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/listpension"))
		.andReturn()
		.getModelAndView();
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@Test
	@WithUserDetails("oovfree")
	public void pensionregister() throws Exception {
		PensionVO vo = new PensionVO();
		vo.setName("컨트롤러 테스트 펜션");
		vo.setCategory(1);
		vo.setAddress("해남군 송지면");
		vo.setLatitude(1.5);
		vo.setLongitude(1.5);
		vo.setBreakfast(true);
		vo.setAirConditioner(true);
		vo.setComments("Hi");
		vo.setConvenienceStore(true);
		vo.setKaraoke(true);
		vo.setUserid("oovfree");
		vo.setEnabled(false);
		vo.setPrice(0L);
		vo.setDelstatus(false);
		vo.setReplyCnt(0);
		
		String jsonStr = new Gson().toJson(vo);
		
		mvc.perform(post("/admin/pensionregister")
				.principal(()-> "oovfree")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr))
				.andExpect(status().is2xxSuccessful());
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@WithMockUser
	@Test
	public void testPensionListWithAd() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/pensionListWithAd"))
		.andReturn()
		.getModelAndView();
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@WithMockUser
	@Test
	public void testPensionAdStatus() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/pensionAdStatus"))
		.andReturn()
		.getModelAndView();
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@WithUserDetails("oovfree")
	@Test
	public void testPensionModify() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/admin/pensionmodify")
		.param("pensionid", "70778")
		.param("comments", "test").principal(()-> "oovfree"))
		.andExpect(status().is2xxSuccessful());
	}
	
	/**
	 *  @author 이대석
	 * 	
	 */
	@WithUserDetails("oovfree")
	@Test
	public void testPensionRemove() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/admin/pensionremove").principal(()-> "oovfree")
				.param("userid", "oovfree")
				.param("pensionid", "70778"))
				.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	@WithUserDetails("yermi")
	public void testListReservation() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/listReservationAll"))
		.andReturn()
		.getModelAndView();
	}
	
	@Test
	@WithUserDetails("yermi")
	public void testListPaymentAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/listPaymentAll"))
		.andReturn()
		.getModelAndView();
	}
	
	@Test
	@WithUserDetails("pension10")
	public void testListPayment() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/admin/listPayment")
				.principal(()->"pension10"))
				.andReturn()
				.getModelAndView();
	}
	
}