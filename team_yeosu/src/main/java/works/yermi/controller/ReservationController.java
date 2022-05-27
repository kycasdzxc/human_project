package works.yermi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import works.yermi.service.PensionService;
import works.yermi.service.RoomService;

@Controller
@RequestMapping("reservation/*")
@AllArgsConstructor
public class ReservationController {
	private PensionService pensionService;
	private RoomService roomService;
	
	@GetMapping("reserve")
	@PreAuthorize("isAuthenticated()")
	public String reserve(Long pensionid, Long roomNum, String date1, String date2, Model model) throws ParseException {
		model.addAttribute("pension", pensionService.get(pensionid));
		model.addAttribute("room", roomService.get(roomNum));
		model.addAttribute("date1", date1);
		model.addAttribute("date2", date2);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate = new Date(dateFormat.parse(date1).getTime());
		Date endDate = new Date(dateFormat.parse(date2).getTime());
		
		long calculate = endDate.getTime() - startDate.getTime();
		int days = (int)(calculate / (1000 * 60 * 60 *24));
		
		model.addAttribute("days", days);
		model.addAttribute("prePayment", roomService.get(roomNum).getPrice() * days);
		
		return "/reservation/reserve";
	}
}
