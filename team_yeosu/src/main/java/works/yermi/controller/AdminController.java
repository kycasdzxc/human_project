package works.yermi.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.Criteria;
import works.yermi.domain.CriteriaRoom;
import works.yermi.domain.MemberVO;
import works.yermi.domain.PageDTO;
import works.yermi.domain.PaymentVO;
import works.yermi.domain.PensionAttachVO;
import works.yermi.domain.PensionVO;
import works.yermi.domain.QnaVO;
import works.yermi.domain.ReservationVO;
import works.yermi.domain.RoomVO;
import works.yermi.mapper.RoomAttachMapper;
import works.yermi.service.MemberService;
import works.yermi.service.PaymentService;
import works.yermi.service.PensionAttachService;
import works.yermi.service.PensionService;
import works.yermi.service.ReservationService;
import works.yermi.service.RoomService;

@Controller
@RequestMapping("admin/*")
@AllArgsConstructor
@Log4j
public class AdminController {
	private PensionService pensionService;
	private MemberService memberService;
	private RoomService roomService;
	private PensionAttachService pensionAttachService;
	private RoomAttachMapper roomAttachMapper;
	private PaymentService paymentService;
	private ReservationService reservationService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("index")
	public String index(Principal principal, Model model) {
		List<PensionVO> list = pensionService.getPensionidByUserid(principal.getName());
		if(list.size() > 0) {
			if(reservationService.sumPrice(list.get(0).getPensionid()) != null) {
				model.addAttribute("sumPrice", reservationService.sumPrice(list.get(0).getPensionid()));
			}
			model.addAttribute("checkReservation", reservationService.checkReservation(list.get(0).getPensionid()));
			model.addAttribute("reservationAtMonth", reservationService.reservationAtMonth(list.get(0).getPensionid()));
		}
		return "/admin/index";
	}
	
	//roomList
	@PreAuthorize("isAuthenticated()")
	@GetMapping("roomList")
	public void roomList(CriteriaRoom cri, Model model) {
		model.addAttribute("roomList", roomService.getList(cri, 6867L));
	}
	@PostMapping
	("roomList")
	public String roomUpdate(RoomVO vo, Model model){
		model.addAttribute("roomList", roomService.modify(vo));
		return "redirect:/admin/roomList";
	}
	
	@PostMapping("remove")
	public String remove(RoomVO vo, Model model){
		model.addAttribute("roomList",roomService.remove(vo.getRoomNum()));
		return "redirect:/admin/roomList";
	}
	
	//roomRegister
	@PreAuthorize("isAuthenticated()")
	@GetMapping("roomRegister")
	public void roomRegister(){}
	
	@PostMapping("roomRegister")
	public String roomReigster(RoomVO vo, Model model){
		vo.setPensionid(59517L);
		vo.setRoomNum(vo.getRoomNum());
		log.info(vo.getRoomNum());
		roomService.register(vo);
		
		vo.getAttachs().forEach(attach -> {
			String url="c:/pension/room/";
			log.info(attach);
			log.info(vo.getRoomNum());
			
			File folder = new File(url, vo.getPensionid().toString()+"/"+vo.getRoomNum().toString());
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			File origin = new File(url, attach.getUuid());
			File origin_s = new File(url, "s_" + attach.getUuid());
			try {
				FileCopyUtils.copy(origin, new File(folder, attach.getUuid()));
				FileCopyUtils.copy(origin_s, new File(folder, "s_" + attach.getUuid()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			origin.delete();
			origin_s.delete();
			attach.setRoomNum(vo.getRoomNum());
			attach.setPath(attach.getPath() +vo.getPensionid().toString()+"/"+ attach.getRoomNum());
			roomAttachMapper.insert(attach);
		});
		log.info(vo.getRoomNum());
		return "redirect:/admin/roomList";
	}
	
	/**
	 *  @author 이대석
	 * 	@param model	
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listpension")
	public String listpension(Model model) {
		model.addAttribute("pension", pensionService.getList());
		return "/admin/listpension";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listpensionAuth")
	public void listpensionAuth(Model model, Criteria cri) {
		model.addAttribute("pension", pensionService.getListWithPaging(cri));
		model.addAttribute("page", new PageDTO(pensionService.getTotalCount(cri), cri));
	}
	
	@PreAuthorize("isAuthenticated()")
	@PutMapping("pensionAuthModify")
	@ResponseBody
	
	
	
	
	public String pensionAuthModify(@RequestBody PensionVO vo) {
		log.info(vo);
		memberService.modifyPensionAuth(vo);
		return "success";
	}
	
	/**
	 *  @author 이대석
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("pensionregister")
	public void pensionRegister() {
	}
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	@PreAuthorize("isAuthenticated() and principal.username == #vo.userid")
	@PostMapping("pensionregister") 
	public String pensionRegister(PensionVO vo) {
		log.info(vo);
		pensionService.register(vo);
		pensionService.registerPublic(vo);
		pensionService.registerInternal(vo);
		pensionService.registerOther(vo);
		
		vo.getAttachs().forEach(attach -> {
			String url = "c:/pension/main/";
			log.info(attach);
			log.info(vo.getPensionid());
			
			File folder = new File(url, vo.getPensionid().toString());
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			File origin = new File(url, attach.getUuid());
			File origin_s = new File(url, "s_" + attach.getUuid());
			try {
				FileCopyUtils.copy(origin, new File(folder, attach.getUuid()));
				FileCopyUtils.copy(origin_s, new File(folder, "s_" + attach.getUuid()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			origin.delete();
			origin_s.delete();
			attach.setPensionid(vo.getPensionid());
			attach.setPath(attach.getPath() + attach.getPensionid());
			pensionAttachService.register(attach);
			
		});
		return "redirect:/admin/index";
	}
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	@GetMapping("pensioncheck") @ResponseBody
	public String pensionCheck(PensionVO vo) {
		return pensionService.findPension(vo) == null ? "0" : "1";
	}
	
	/**
	 *  @author 이대석
	 * 	@param pensionid
	 * 	@param model	
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("pensionmodify")
	public void pensionModify(Long pensionid, Model model) {
		model.addAttribute("pension", pensionService.get(pensionid));
	}
	
	/**
	 *  @author 이대석
	 * 	@param vo	
	 */
	@PreAuthorize("isAuthenticated() and principal.username == #vo.userid")
	@PostMapping("pensionmodify")
	public String pensionModify(PensionVO vo) {
		log.info(vo);
		
		pensionService.modifyPension(vo);
		pensionService.modifyPublic(vo);
		pensionService.modifyInternal(vo);
		pensionService.modifyOther(vo);
		
		return "redirect:/admin/listpension";
	}
	
	/**
	 *  @author 이대석
	 * 	@param pensionid
	 * 	@param rttr
	 * 	@param uc
	 * 	@param vo	
	 */
	@PreAuthorize("isAuthenticated() and principal.username == #vo.userid")
	@PostMapping("pensionremove")
	public String remove(Long pensionid,  RedirectAttributes rttr,UploadController uc, PensionVO vo) {
		log.info(pensionid);
		List<PensionAttachVO> attachs = vo.getAttachs();
		log.info(attachs);
		if(pensionService.removePension(pensionid)) {
			rttr.addFlashAttribute("result", "삭제");
			if(attachs != null) attachs.forEach(uc::deleteFile);
		}
		pensionService.removePublic(pensionid);
		pensionService.removeInternal(pensionid);
		pensionService.removeOther(pensionid);
		
		return "redirect:/admin/listpension";
	}
	
	/**
	 *  @author 이대석
	 * 	@param model
	 * 	@param cri
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("pensionAdStatus")
	public void pensionAdStatus(Model model, Criteria cri) {
		model.addAttribute("pension", pensionService.getListWithNotAd(cri));
		model.addAttribute("page", new PageDTO(pensionService.getTotalCount(cri), cri));
	}
	
	/**
	 *  @author 이대석
	 * 	@param model
	 * 	@param pensionid
	 */
	@PostMapping("pensionAdStatus") 
	public String pensionAdStatusModify(Model model, Long pensionid) {
		model.addAttribute("pension", pensionService.modifyAd(pensionid));
		return "redirect:/admin/pensionAdStatus";
	}
	
	/**
	 *  @author 이대석
	 * 	@param model
	 * 	@param cri
	 */
	@PreAuthorize("isAuthenticated()")
	@GetMapping("pensionListWithAd")
	public void pensionListWithAd(Model model, Criteria cri) {
		model.addAttribute("pension", pensionService.ListWithAd(cri));
		model.addAttribute("page", new PageDTO(pensionService.getTotalCount(cri), cri));
	}
	
	/**
	 *  @author 이대석
	 * 	@param model
	 * 	@param pensionid
	 */
	@PostMapping("pensionListWithAd")
	public String pensionRemoveAd(Model model, Long pensionid) {
		model.addAttribute("pension", pensionService.removeAd(pensionid));
		return "redirect:/admin/pensionListWithAd";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("memberList")
	public void memberList(Model model, Criteria cri) {
		log.info("admin/memberList()");
		model.addAttribute("members", memberService.list(cri));
		model.addAttribute("page", new PageDTO(memberService.getTotalCount(cri), cri));
	}

	@PostMapping("memberList")
	public String memberListModify(Model model, MemberVO memberVO) {
		model.addAttribute("members", memberService.modify(memberVO));
//		model.addAttribute("members", memberService.remove(memberVO));
		memberService.remove(memberVO);
		log.info(memberVO);
		return "redirect:/admin/memberList"; 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listPayment")
	public String listPayment(Principal principal, Model model) {
		List<PensionVO> list  = pensionService.getPensionidByUserid(principal.getName());
		log.info("확인 ::" + list);
		log.info("확인2 ::" + paymentService.getList(list.get(0).getPensionid()));
		if(list.size() > 0) {
			model.addAttribute("reservation", paymentService.getList(list.get(0).getPensionid()));
			return "/admin/listPayment";
		}
		else {
			return "redirect:/admin/index";
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listPaymentAll")
	public String listPaymentAll(Principal principal, Model model) {
		model.addAttribute("pension", paymentService.getListAll());
		return "/admin/listPaymentAll";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listReservation")
	public String listReservation(Principal principal, Model model) {
		List<PensionVO> list  = pensionService.getPensionidByUserid(principal.getName());
		if(list.size() > 0) {
			model.addAttribute("reservation", reservationService.getListByPensionid(list.get(0).getPensionid()));
			return "/admin/listReservation";
		}
		else {
			return "redirect:/admin/index";
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("listReservationAll")
	public String listReservationAll(Principal principal, Model model) {
		model.addAttribute("reservation", reservationService.getList());
		return "/admin/listReservationAll";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("checkReservation")
	public String checkReservation(Principal principal, Model model) {
		List<PensionVO> list  = pensionService.getPensionidByUserid(principal.getName());
		if(list.size() > 0) {
			model.addAttribute("reservation", reservationService.getListCheckReservation(list.get(0).getPensionid()));
			return "/admin/checkReservation";
		}
		else {
			return "redirect:/admin/index";
		}
	}
	
	@GetMapping("cancelReservation")
	public String cancelReservation(Long reservationNum) {
		reservationService.cancelReservation(reservationNum);
		return "redirect:/admin/checkReservation";
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@GetMapping("listInquiry")
	public void listTotalCountQna(Model model, Criteria cri) {
		model.addAttribute("list", memberService.listQna(cri));
		model.addAttribute("page", new PageDTO(memberService.getTotalCountQna(cri), cri));
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
	@PostMapping("listInquiry")
	public String QnaRegister(RedirectAttributes rttr, Criteria cri, QnaVO vo, Model model) {
		Long bno = memberService.register(vo);
		if(bno != null) {
			rttr.addFlashAttribute("bno", bno);
		}
		return "redirect:/admin/listInquiry" + cri.getParams();
	}
}
