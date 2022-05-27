package works.yermi.mapper;

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
public class PensionMapperTests {
	@Setter @Autowired
	private PensionMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(log::info);
	}
	
	@Test
	public void testGetListTopten() {
		mapper.getListTopten().forEach(log::info);
	}
	
	@Test
	public void testGetListWithFilter() {
		PensionVO vo = new PensionVO();
		CriteriaPension cri = new CriteriaPension();
		vo.setCategory(1);
		
		cri.setAmount(10);
		cri.setLastPensionid(6687L);
		
		mapper.getListWithFilter(cri, vo).forEach(log::info);;
	}
	
	@Test
	public void testRead() {
		PensionVO result = mapper.read(70705L);
		log.info(result);
	}
	
	@Test
	public void findPension() {
		PensionVO vo = new PensionVO();
		vo.setName("까르마 풀빌라");
		mapper.findPension(vo);
		
	}
	
	@Test
	public void testInsert() {
		PensionVO vo = new PensionVO();
		vo.setName("매퍼테스트");
		vo.setUserid("oovfree");
		vo.setCategory(1);
		vo.setAddress("주소");
		vo.setEnabled(true);
		vo.setLatitude(1.5);
		vo.setLongitude(1.5);
		vo.setPrice(0L);
		vo.setComments("테스트");
		vo.setBathTub(true);
		vo.setBbq(true);
		vo.setCampfire(true);
		vo.setConvenienceStore(true);
		vo.setAirConditioner(true);
		vo.setFootVolleyballCourt(true);
		vo.setPickup(true);
		
		mapper.insertSelectKey(vo);
		mapper.insertPublic(vo);
		mapper.insertInternal(vo);
		mapper.insertOther(vo);
		
		log.info(mapper.read(vo.getPensionid()));
	}
	
	@Test
	public void updatePension() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		vo.setComments("수정");
		vo.setCategory(2);
		int result = mapper.updatePension(vo);
		int exp = 1;
		assertEquals("수정", exp, result);
		
	}
	
	@Test
	public void updatePublic() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		vo.setFootVolleyballCourt(false);
		vo.setParkingLot(true);
		int result = mapper.updatePublic(vo);
		int exp = 1;
		assertEquals("수정", exp, result);
		
	}
	
	@Test
	public void updateInternal() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		vo.setWifi(false);
		vo.setTv(true);
		vo.setRefrigerator(true);
		int result = mapper.updateInternal(vo);
		int exp = 1;
		assertEquals("수정", exp, result);
	}
	
	@Test
	public void updateOther() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		vo.setCooking(true);
		vo.setPickup(true);
		int result = mapper.updateOther(vo);
		int exp = 1;
		assertEquals("수정", exp, result);
	}
	
	@Test
	public void deletePension() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		int result = mapper.deletePension(vo.getPensionid());
		int exp = 1;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void deletePublic() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		int result = mapper.deletePublic(vo.getPensionid());
		int exp = 1;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void deleteInternal() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		int result = mapper.deleteInternal(vo.getPensionid());
		int exp = 1;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void deleteOther() {
		PensionVO vo = mapper.read(70705L);
		log.info(vo);
		int result = mapper.deleteOther(vo.getPensionid());
		int exp = 1;
		assertEquals("삭제", exp, result);
	}
	
	@Test
	public void testGetPensionidByUserid() {
		PensionVO vo = mapper.getPensionidByUserid("oovfree").get(0);
		log.info(vo);
	}
	
	@Test
	public void updateReplyCnt() {
		Long pensionid = 70702L;
		int result = mapper.updateReplyCnt(pensionid);
		int exp = 1;
		assertEquals("수정", exp, result);
	}
	
	@Test
	public void updateStarRate() {
		Long pensionid = 70702L;
		int result = mapper.updateStarRate(pensionid);
		int exp = 1;
		assertEquals("수정", exp, result);
	}
	
	@Test
	public void updatePrice() {
		Long pensionid = 70702L;
		int result = mapper.updatePrice(pensionid);
		int exp = 1;
		assertEquals("수정", exp, result);
	}
	
	@Test
	public void testUpdateEnabled() {
		PensionVO pensionVO = new PensionVO();
		pensionVO.setPensionid(70384L);
		pensionVO.setEnabled(false);
		mapper.updateEnabled(pensionVO);
		
	}
	
//	@Test 
//	public void test1() {
//		mapper.test1().forEach(m->{
//			mapper.test2(m);
//		});
//	}
//	
//	@Test
//	public void test2() {
//		mapper.test3().forEach(m -> {
//			mapper.test4(m);
//		});
//	}
//	
//	@Test
//	public void test3() {
//		mapper.test5().forEach(m -> {
//			mapper.test6(m);
//		});
//	}
}
