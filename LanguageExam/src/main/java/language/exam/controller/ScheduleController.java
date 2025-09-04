package language.exam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import language.exam.domain.exams.model.Bookings;
import language.exam.domain.exams.model.Infos;
import language.exam.domain.exams.service.BookingsService;
import language.exam.domain.exams.service.InfosService;

@Controller
public class ScheduleController {
	
	@Autowired
	private BookingsService bookingsService;
	
	@Autowired
	private InfosService infosService;
	
	@GetMapping("/exam-schedule") 
	public String getExamSchedule(@AuthenticationPrincipal(expression = "id") Integer accountId, Model model) {
		List<Bookings> bookings = bookingsService.getBookings(accountId);
		model.addAttribute("bookings", bookings);
		
		Date today = new Date();
		model.addAttribute("today", today);
		
		List<Infos> infos = infosService.getAllInfos();
		model.addAttribute("infos", infos);
		return "schedule/exam-schedule";
	}
}