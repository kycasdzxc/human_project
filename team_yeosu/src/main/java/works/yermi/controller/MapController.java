package works.yermi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.PensionVO;
import works.yermi.service.PensionService;

@Controller
@RequestMapping("map")
@AllArgsConstructor
@Log4j
public class MapController {
	private final PensionService service;

	@GetMapping("")
	public void map(){}
	
	@GetMapping("map") @ResponseBody
    public List<PensionVO> getList() {
		log.warn("map");
		return service.getList();
	}
	
}
