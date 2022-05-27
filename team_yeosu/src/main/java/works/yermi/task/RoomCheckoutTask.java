package works.yermi.task;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.RoomVO;
import works.yermi.service.RoomService;

@Log4j
@Component
@AllArgsConstructor
public class RoomCheckoutTask {
	RoomService service;
	
	@Scheduled(cron="0 0 11 * * *")
	public void checkFiles(){
		log.info("===================================");
		log.info("    금일 checkout을 시작합니다.    ");
		log.info("===================================");
		
		List<RoomVO> list = service.getCheckoutList();
		
		for(RoomVO vo : list) {
			service.checkoutRoom(vo.getRoomNum());
			log.warn(vo.getRoomNum() + "번 방이 퇴실처리 되었습니다.");
		}
		
		log.info("===================================");
		log.info("    금일 checkout을 종료합니다.    ");
		log.info("===================================");
		
		
		
	}
}
