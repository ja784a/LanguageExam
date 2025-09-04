package language.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import language.exam.domain.exams.model.Bookings;
import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.service.BookingsService;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.FeesService;

@Controller
public class BookingController {
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private BookingsService bookingsService;
	
	@GetMapping("/confirm-booking")
	public String getConfirmBooking(HttpSession session, Model model) {
		Integer examId = (Integer) session.getAttribute("examId");
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		Integer gradeId = (Integer) session.getAttribute("gradeId");
		
		ExamInfos examInfo = examInfosService.getExamInfo(examId);
		
		Fees fee = feesService.getFee(subjectId, gradeId);
		
		model.addAttribute("examInfo", examInfo);
		model.addAttribute("fee", fee);
		
		return "booking/confirm-booking";
	}
	
	@PostMapping("/confirm-booking") 
	public String postConfirmBooking(HttpSession session, @AuthenticationPrincipal(expression = "id") Integer accountId) {
		Integer examId = (Integer) session.getAttribute("examId");
		
		Bookings booking = new Bookings();
		booking.setAccountId(accountId);
		booking.setExamId(examId);
		bookingsService.addBooking(booking);
		
		session.removeAttribute("subjectId");
		session.removeAttribute("gradeId");
		session.removeAttribute("placeId");
		session.removeAttribute("examId");
		
		return "redirect:/complete-booking";
	}
	
	@GetMapping("complete-booking") 
	public String getCompleteBooking() {
		return "booking/complete-booking";
	}
}