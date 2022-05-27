package works.yermi.service;

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
public class ReservationServiceTests {
	@Setter @Autowired
	private ReservationService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(log::info);
	}
	
	@Test
	public void testGetListByUserid() {
		service.getListByUserid("id1").forEach(log::info);
	}
	
	@Test
	public void testGetListByPensionid() {
		service.getListByPensionid(11091L).forEach(log::info);
	}
	
	@Test
	public void testGetListCheckReservation() {
		service.getListCheckReservation(11091L);
	}
	
	@Test
	public void testReserve() {
		ReservationVO vo = new ReservationVO();
		vo.setReservationNum(123456789L);
		vo.setRoomNum(8698L);
		vo.setPaymentPrice(12345);
		vo.setUserid("id2");
		vo.setPensionid(11091L);
		vo.setCheckin("22/09/22");
		vo.setCheckout("22/09/25");
		log.info(vo);
		
		service.reserve(vo);
	}
	
	@Test
	public void cancelReservation() {
		service.cancelReservation(75731653023931358L);
	}
	
	@Test
	public void testDelete() {
		service.delete(83L);
	}
	
	@Test
	public void testSumPrice() {
		service.sumPrice(6867L);
	}
	
	@Test
	public void testCheckReservation() {
		service.checkReservation(6867L);
	}
	
	@Test
	public void testReservationAtMonth() {
		service.reservationAtMonth(6867L);
	}
}
