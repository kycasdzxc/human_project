package works.yermi.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import works.yermi.domain.ReservationVO;
import works.yermi.service.PensionService;
import works.yermi.service.ReservationService;

@Controller
@Log4j
public class HomeController {
	private IamportClient api;
	@Setter @Autowired
	private PensionService service;
	@Setter @Autowired
	private ReservationService reservationService;
	
	public HomeController() {
    	// REST API 키와 REST API secret 를 아래처럼 순서대로 입력한다.
		this.api = new IamportClient("1210591057986564","636844b6763b6a48220496073ce58a7dfca7f1e13a8b832a716138166375818f63b969ba747cf090");
	}
		
	@ResponseBody
	@RequestMapping(value="/verifyIamport/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(
			Model model
			, @RequestBody ReservationVO reservation
			, Locale locale
			, HttpSession session
			, @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
	{	
			log.info(reservation);
			reservationService.reserve(reservation);
			return api.paymentByImpUid(imp_uid);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/index";
	}
	
	/**
	 *  @author 이대석
	 */
	@GetMapping("index")
    public void index(Model model) {
		model.addAttribute("pension", service.getListTopten());
		model.addAttribute("pensions", service.getListWithAd());
	}
}
