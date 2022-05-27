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
import works.yermi.domain.PaymentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class PaymentMapperTests {
	@Setter @Autowired
	private PaymentMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	
	@Test
	public void testInsert(){
		PaymentVO vo = new PaymentVO();
		vo.setPaymentNum(101L);
		vo.setPaymentPrice(10000);
		vo.setUserid("mailtest");
		vo.setRoomNum(8713L);
		vo.setPensionid(6867L);
		
		mapper.insert(vo);
	}
	
	@Test
	public void testGetList() {
		mapper.getList(11091L).forEach(log::info);
	}
	
	@Test
	public void testGetListAll() {
		mapper.getListAll().forEach(log::info);
	}
	
	@Test
	public void testUpdateStatus() {
		int exp = 1;
		int result = mapper.updateStatus(75731653023931358L);
		
		assertEquals("수정", result, exp);
	}
}
