package works.yermi.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.Criteria;
import works.yermi.domain.CustomUser;
import works.yermi.domain.MemberVO;
import works.yermi.domain.PageDTO;
import works.yermi.domain.QnaVO;
import works.yermi.service.MemberService;
import works.yermi.service.ReservationService;

@Controller
@RequestMapping("member")
@AllArgsConstructor
@Log4j
public class MemberController {
	private MemberService memberService; 
	private ReservationService reservationService;
	
	@GetMapping("login")
	public void login(){
		log.info("login");
	}

	@GetMapping("join")
	public void join(){
		log.info("join");
	}
	
	@PostMapping("join")
	public String join(MemberVO vo){
		log.info(vo);
		memberService.join(vo);
		return "redirect:/member/login";
	}
	
//	@GetMapping({"get", "modify"})
//	@PreAuthorize("isAuthenticated()")
//	public void get(Principal principal, Model model, Authentication authentication){
//		log.info(authentication);
//		log.info(principal);
////		MemberVO vo = memberService.get(principal.getName());
////		log.info(vo);
////		model.addAttribute("member", vo);
//	}
	
	@GetMapping({"get", "modify"})
    @PreAuthorize("isAuthenticated()")
    public void get(Model model, @AuthenticationPrincipal CustomUser customUser){
        log.info(customUser);
        MemberVO vo = memberService.get(customUser.getUsername());
        log.info(vo);
        model.addAttribute("member", vo);
    }
	
	@PreAuthorize("isAuthenticated() and principal.username == #memberVO.userId")
	@PostMapping("modify")
	public String modify(MemberVO memberVO, RedirectAttributes rttr){
		log.info("modify : " + " " + memberVO);
		
		if(memberService.modify(memberVO)){
			rttr.addFlashAttribute("result", "수정");
		}
		return "redirect:/member/mypage";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("mypage")
	public void mypage(){
		
	}
	
	@PostMapping("mypage")
	public void mypage(MemberVO vo){
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("mypage/listReservation")
	public void mypageListReservation(Principal principal, Model model){
		log.info("id : " + principal.getName());
		log.info("list : " + reservationService.getListByUserid(principal.getName()));
		model.addAttribute("reservation", reservationService.getListByUserid(principal.getName()));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("mypage/listPoint")
	public void mypageListPoint(){
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("mypage/listReply")
	public void mypageListReply(){
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("mypage/listInquiry")
	public void mypageListInquiry(Model model, Principal principal){
		model.addAttribute("list", memberService.findQnaByWriter(principal.getName()));
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("mypage/listInquiry")
	public String mypageListInquiryRegister(QnaVO vo) {
		log.info(vo);
		memberService.register(vo);
		return "redirect:/member/mypage/listInquiry";
	}
	
	@RequestMapping(value="juso", method={RequestMethod.GET, RequestMethod.POST})
	public void jusoPopup(String str){
		log.info(str);
	}
	
	@GetMapping("idcheck") @ResponseBody
	public String idCheck(MemberVO memberVO){
		log.info(memberVO.getUserId());  // 존재할때 0 인지 확인 
		log.info(memberVO.getNickName());   
		log.info(memberVO.getEmail());  
		// 0 null , 1 exist 
		return memberService.hasMember(memberVO) == null ? "0" : "1";
	}
	
	// 메일 발송 
	@GetMapping("sendmail") @ResponseBody
	public String sendmail(String email, String userId) throws Exception {
		log.info("email : " + email);
		log.info("userId : " + userId);
		memberService.mailSender(email, userId);
		
		return "success";
		
	}
	// 메일 확인 후 인증	
	@GetMapping("procAuth")
	public String procAuth(String userId, String authToken, RedirectAttributes rttr){
		log.info("userId : " + userId);
		log.info("authToken : " + authToken);
		rttr.addFlashAttribute("result", memberService.mailConfirm(userId, authToken));
		return "redirect:modify";
	}
}
