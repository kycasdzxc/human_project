package works.yermi.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.config.RootConfig;
import works.yermi.config.SecurityConfig;
import works.yermi.domain.ReservationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class ReservationMapperTests {
	@Setter @Autowired
	private ReservationMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(log::info);
	}
	
	@Test
	public void testGetListByUserid() {
		mapper.getListByUserid("id1").forEach(log::info);
	}
	
	@Test
	public void testGetListByPensionid() {
		mapper.getListByPensionid(62735L).forEach(log::info);
	}
	
	@Test
	public void testCheckDate() {
		ReservationVO vo = new ReservationVO();
		vo.setCheckin("22/06/22");
		vo.setCheckout("22/05/29");
		vo.setRoomNum(8698L);
		
		mapper.checkDate(vo.getCheckin(), vo.getCheckout(), vo.getRoomNum()).forEach(log::info);
		
	}
	
	@Test
	public void testInsert() {
		ReservationVO vo = new ReservationVO();
		vo.setReservationNum(12345657L);
		vo.setCheckin("22/08/25");
		vo.setCheckout("22/08/28");
		vo.setRoomNum(8698L);
		vo.setUserid("id2");
		vo.setPensionid(11091L);
		log.info(vo);
		
		int exp = 1;
		int result = 0;
		mapper.checkDate(vo.getCheckin(), vo.getCheckout(), vo.getRoomNum()).forEach(log::info);
		
		int size = mapper.checkDate(vo.getCheckin(), vo.getCheckout(), vo.getRoomNum()).size();
		
		log.info("size :: " + size);
		
		if(size > 0) {
			log.info("예약 실패");
		}
		else {
			result = mapper.insert(vo);
			log.info("result :: " + result);
			log.info("예약 성공");
		}
		
		assertEquals("추가", result, exp);
	}
	
	@Test
	public void testUpdateStatus() {
		int exp = 1;
		
		int result = mapper.updateStatus(47L);
		
		assertEquals("수정", result, exp);
	}
	
	@Test
	public void testDelete() {
		int exp = 1;
		
		int result = mapper.delete(47L);
		
		assertEquals("삭제", result, exp);
	}
	
	@Test
	public void testGetListCheckReservation() {
		mapper.getListCheckReservation(11091L);
	}
	
	@Test
	public void testRead() {
		mapper.read(75731653023931358L);
	}
	
	@Test
	public void testSumPrice() {
		mapper.sumPrice(6867L);
	}
	
	@Test
	public void testCheckReservation() {
		mapper.checkReservation(6867L);
	}
	
	@Test
	public void testReservationAtMonth() {
		mapper.reservationAtMonth(6867L);
	}
}
