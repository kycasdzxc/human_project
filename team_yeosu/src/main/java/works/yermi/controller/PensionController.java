package works.yermi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.Criteria;
import works.yermi.domain.CriteriaPension;
import works.yermi.domain.PensionAttachVO;
import works.yermi.domain.PensionVO;
import works.yermi.domain.ReplyVO;
import works.yermi.service.PensionAttachService;
import works.yermi.service.PensionService;
import works.yermi.service.ReplyService;

@Controller
@RequestMapping("pension")
@AllArgsConstructor
@Log4j
public class PensionController {
	private final PensionService service;
	private final PensionAttachService pensionAttachService;
	private ReplyService replyService;
	
	/**
	 *  @author 이대석, 김예찬
	 * 	@param startDate
	 * 	@param endDate
	 * 	@param model	
	 */
	@GetMapping("list")
	public void list (String startDate, String endDate, Model model){
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pensions", service.getListTopten());
	}
	
	/**
	 *  @author 이대석
	 * 	@param lastPensionid
	 * 	@param amount
	 *  @param vo	
	 */
	@GetMapping({"pages/{lastPensionid}", "pages/{lastPensionid}/{amount}"})
	@ResponseBody
	public List<PensionVO> getList(
		@PathVariable(required=false) Optional<Long> lastPensionid,
		@PathVariable(required=false) Optional<Integer> amount
		, PensionVO vo) {
		log.info(vo);
		
		CriteriaPension cri = new CriteriaPension();
		cri.setLastPensionid(lastPensionid.orElse(cri.getLastPensionid()));
		cri.setAmount(amount.orElse(cri.getAmount()));
		
		return service.getListWithFilter(cri, vo);
	}
	
	/**
	 *  @author 이대석, 김예찬
	 * 	@param pensionid
	 * 	@param startDate
	 * 	@param endDate
	 * 	@param model	
	 */
	@GetMapping("detail")
	public void detail(Long pensionid, String startDate, String endDate, Model model) {
		model.addAttribute("pension", service.get(pensionid));
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
	}
	
	@PostMapping("detail")
	public String register(ReplyVO replyVO, RedirectAttributes rttr) {
		log.info("들어왔다! : " + replyVO);
		replyService.register(replyVO);
		rttr.addFlashAttribute("result", replyVO.getRno());
		rttr.addAttribute("title", replyVO.getTitle());
		rttr.addAttribute("content", replyVO.getContent());
		rttr.addAttribute("starRate", replyVO.getStarRate());
		rttr.addAttribute("nickName", replyVO.getNickName());
		rttr.addAttribute("pensionid", replyVO.getPensionid());
		return "redirect:/pension/detail?pensionid=" + replyVO.getPensionid();
		
	}
	
	/**
	 *  @author 이대석
	 * 	@param pensionid	
	 */
	@GetMapping("attachs") @ResponseBody
	public List<PensionAttachVO> getAttachs(Long pensionid) {
		return pensionAttachService.getList(pensionid);
	}
}
