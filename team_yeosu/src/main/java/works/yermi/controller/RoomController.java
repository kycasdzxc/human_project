package works.yermi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.RoomVO;
import works.yermi.service.RoomService;

@Controller
@RequestMapping("room/*")
@AllArgsConstructor
public class RoomController {
	private final RoomService service;
	
	
	@GetMapping({"pages/{pensionid}","pages/{pensionid}/{lastRoomNum}", "pages/{pensionid}/{lastRoomNum}/{amount}"})
	@ResponseBody
	public List<RoomVO> getList(
			@PathVariable Long pensionid, 
			@PathVariable(required=false) Optional<Long> lastRoomNum,
			@PathVariable(required=false) Optional<Integer> amount) {
		CriteriaRoom cri = new CriteriaRoom();
		
		cri.setLastRoomNum(lastRoomNum.orElse(cri.getLastRoomNum()));
		cri.setAmount(amount.orElse(cri.getAmount()));
		return service.getList(cri, pensionid);
	}
	
}
