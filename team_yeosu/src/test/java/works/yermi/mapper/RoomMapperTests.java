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
import works.yermi.domain.RoomVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class,SecurityConfig.class})
@Log4j
public class RoomMapperTests {
	@Autowired @Setter
	private RoomMapper mapper;
	
	@Test
	public void testExist(){
		assertNotNull(mapper);
		log.info(mapper);
	}
	
	@Test
	public void testGetList() {
		RoomVO vo= new RoomVO();
		vo.setPensionid(777777L);
		mapper.getList(vo.getPensionid()).forEach(log::info);
	}
	
	@Test
	public void testRead() {
		RoomVO vo = mapper.read(1111L);
		log.info(vo);
	}
	
	@Test
	public void testInsert() {
		RoomVO vo = new RoomVO();
		vo.setPensionid(59517L);
		vo.setDeadline("22/06/21");
		vo.setReservationStatus(true);
		vo.setPrice(9999L);
		vo.setEnabled(true);
		vo.setRoomName("pollapo");
		int exp = 1;
		int result = mapper.insert(vo);
		
		assertEquals("추가", result, exp);
	}
	
	@Test
	public void testUpdate() {
		RoomVO vo = new RoomVO();
		vo.setRoomNum(1111L);
		vo.setDeadline("22/06/21");
		vo.setPrice(99999L);
		vo.setRoomName("merona");
		
		int exp = 1;
		
		int result = mapper.update(vo);
		
		assertEquals("수정", result, exp);
		
	}
	@Test
	public void testDelete() {
		Long roomNum = 42313L;
		
		int exp = 1;
		
		int result = mapper.delete(roomNum);
		
		assertEquals("삭제", result, exp);
	}
	
	@Test
	public void testReserveRoom() {
		mapper.reserveRoom(8698L, "22/05/18", "22/05/20");
	}
	
	@Test
	public void testCheckoutRoom() {
		mapper.checkoutRoom(8702L);
	}
	
	@Test
	public void testgetCheckoutList() {
		mapper.getCheckoutList();
	}
}
