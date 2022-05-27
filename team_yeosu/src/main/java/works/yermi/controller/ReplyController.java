package works.yermi.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.CriteriaReply;
import works.yermi.domain.ReplyAttachVO;
import works.yermi.domain.ReplyVO;
import works.yermi.service.ReplyService;

@Controller
@RequestMapping("reply/*") // 을 통해서 어느 컨트롤러가 요청을 처리할지 알게 된다.
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService service;
	
	// 등록
	@PostMapping("new")
	public Long create(@RequestBody ReplyVO vo) {
		log.info(vo);
		return service.register(vo) > 0 ? vo.getRno() : null;
	}
	
	// 목록 / 별점평균, 페이지처리
	@GetMapping({"pages/{pensionid}", "pages/{pensionid}/{lastRno}", "pages/{pensionid}/{lastRno}/{amount}"})
	@ResponseBody // xml, .json 형태로 보기위한 어노테이션
	public Map<String, Object> getList(
			@PathVariable Long pensionid,
			@PathVariable(required=false) Optional<Long> lastRno, 
			@PathVariable(required=false) Optional<Integer> amount) {
		log.info("list가 또착");
		CriteriaReply cri = new CriteriaReply();
		cri.setLastRno(lastRno.orElse(cri.getLastRno()));
		cri.setAmount(amount.orElse(cri.getAmount()));
		Map<String, Object> list = service.getList(pensionid, cri);
		log.info("list두번째도착");
		return list;
	}
	
	// 조회
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable Long rno) {
		log.info("get : " + rno);
		return service.get(rno);
	}
	
	// 삭제
	@DeleteMapping("{rno}")
	public String remove(@PathVariable Long rno, @RequestBody ReplyVO vo) {
		log.info("remove : " + rno);
		return service.remove(rno) > 0 ? "success" : null;
	}
	
	// 수정
	@RequestMapping(value="{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	@ResponseBody // 리턴타임이 String면 리턴값의 jsp를 찾는다 하지만 이 어노테이션을 사용하게 되면 해당 String 자체를 jsp로 가져간다!
	public String modify(@PathVariable Long rno, @RequestBody ReplyVO vo) {
		log.info("rno : " + rno);
		log.info("modify : " + vo);
		return service.modify(vo) > 0 ? "success" : null;
	}
	
	@GetMapping("attachs") @ResponseBody
	public List<ReplyAttachVO> getAttachs(Long rno) {
		log.info(rno);
		return service.getAttach(rno);
	}
}

/*
 * 등록 > /reply/new > POST
 * 조회 > /reply/:rno > GET
 * 삭제 > /reply/:rno > DELETE
 * 수정 > /reply/:rno > PUT OR PATCH
 * 페이지 > /reply/pages/:pensionid/:page > GET
 */

