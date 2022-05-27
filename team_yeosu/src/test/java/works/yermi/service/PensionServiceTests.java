package works.yermi.service;

import static org.junit.Assert.assertEquals;
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
import works.yermi.domain.CriteriaPension;
import works.yermi.domain.PensionVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class PensionServiceTests {
	@Setter @Autowired
	private PensionService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(log::info);
	}
	
	@Test
	public void testGetListWithFilter() {
		PensionVO vo = new PensionVO();
		CriteriaPension cri = new CriteriaPension();
		vo.setCategory(1);
		vo.setParkingLot(true);
		vo.setBbq(true);
		vo.setWifi(true);
		vo.setCooking(true);
		vo.setFreeParking(true);
		service.getListWithFilter(cri, vo).forEach(log::info);
	}
	
	@Test
	public void testGetListTopten() {
		service.getListTopten().forEach(log::info);
	}
	
	@Test
	public void testGet() {
		PensionVO result = service.get(70596L);
		assertNotNull(result);
		log.info(result);
	}
	
	@Test
	public void testFindPension() {
		PensionVO vo = new PensionVO();
		vo.setName("까르마 풀빌라");
		service.findPension(vo);
	}
	
	@Test
	public void testReigster() {
		PensionVO vo = new PensionVO();
		vo.setName("testpension");
		vo.setUserid("oovfree");
		vo.setCategory(2);
		vo.setFootVolleyballCourt(true);
		vo.setConvenienceStore(true);
		vo.setAirConditioner(true);
		vo.setBathTub(true);
		vo.setAddress("여수 어딘가");
		vo.setComments("test");
		vo.setAddress("우리집");
		vo.setLongitude(1.5);
		vo.setLatitude(1.5);
		
		int exp = 1;
		
		int result = service.register(vo);
		
		assertEquals("펜션추가", exp, result);
	}
	
	@Test
	public void testModify() {
		PensionVO vo = service.get(70706L);
		log.info(vo);
		vo.setName("수정");
		vo.setCategory(1);
		vo.setAirConditioner(false);
		vo.setBathTub(true);
		vo.setCooking(true);
		vo.setAddress("수정 주소");
		vo.setKaraoke(true);
		
		service.modifyPension(vo);
		service.modifyPublic(vo);
		service.modifyInternal(vo);
		service.modifyOther(vo);
		
		log.info(vo);
	}
	
	@Test
	public void testRemove() {
		Long pensionid = 70706L;
		
		service.removePension(pensionid);
		service.removePublic(pensionid);
		service.removeInternal(pensionid);
		service.removeOther(pensionid);
		
		log.info(service.get(pensionid));
	}
	
	@Test
	public void testGetPensionidByUserid() {
		log.info(service.getPensionidByUserid("pension3").get(0).getPensionid());
	}

}
