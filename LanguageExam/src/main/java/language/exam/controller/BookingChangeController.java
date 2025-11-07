package language.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import language.exam.domain.exams.model.Bookings;
import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Places;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.BookingsService;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.FeesService;
import language.exam.domain.exams.service.GradesService;
import language.exam.domain.exams.service.PlacesService;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.form.GroupOrder;
import language.exam.form.SelectExamDateForm;
import language.exam.form.SelectPlaceForm;

@Controller
public class BookingChangeController {
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private SubjectsService subjectsService;
	
	@Autowired
	private GradesService gradeService;
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private BookingsService bookingsService;
	
	@GetMapping("/change-place/{id}")
	public String getChangePlace(@PathVariable("id") Integer examId, @ModelAttribute SelectPlaceForm selectPlaceForm, Model model, HttpSession session) {
		ExamInfos examInfo = examInfosService.getExamInfo(examId);
		
		if (examInfo == null) {
			return "error/error";
		} else {
			model.addAttribute("subject",examInfo.getSubjects().getSubject());
			model.addAttribute("grade", examInfo.getGrades(). getGrade());
			model.addAttribute("examId", examId);
			
			List<Places> places = placesService.getAllPlaces();
			model.addAttribute("places", places);
			
			selectPlaceForm.setSubjectId(examInfo.getSubjectId());
			selectPlaceForm.setPlaceId(examInfo.getPlaces().getId());
	
			session.setAttribute("gradeId", examInfo.getGradeId());
			session.setAttribute("oldExamId", examId);
			
			return "booking-change/change-place";
		}
	}
	
	@PostMapping("/change-place/{id}")
	public String postChangePlace(@PathVariable("id") Integer examId, HttpSession session, @Validated(GroupOrder.class) SelectPlaceForm selectPlaceForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getChangePlace(examId, selectPlaceForm, model, session);
		} else {
			session.setAttribute("placeId", selectPlaceForm.getPlaceId());
			session.setAttribute("subjectId", selectPlaceForm.getSubjectId());
			return "redirect:/change-exam-date";
		}
	}
	
	@GetMapping("change-exam-date")
	public String getChangeExamDate(HttpSession session, @ModelAttribute SelectExamDateForm selectExamDateForm, Model model) {
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		Subjects subject = subjectsService.getSubject(subjectId);
		model.addAttribute("subject", subject);
		
		Integer gradeId = (Integer) session.getAttribute("gradeId");
		Grades grade = gradeService.getGrade(gradeId);
		model.addAttribute("grade", grade);
		
		Integer placeId = (Integer) session.getAttribute("placeId");
		Places place = placesService.getPlace(placeId);
		model.addAttribute("place", place);
		
		List<ExamInfos> examDates = examInfosService.getExamInfosForUsers(subjectId, gradeId, placeId);
		model.addAttribute("examDates", examDates);
		
		Integer examId = (Integer) session.getAttribute("oldExamId");
		model.addAttribute("oldExamId", examId);
		
		selectExamDateForm.setExamId(examId);
		
		return "booking-change/change-exam-date";
	}
	
	@PostMapping("/change-exam-date")
	public String postChangeExamDate(HttpSession session, @Validated(GroupOrder.class) SelectExamDateForm selectExamDateForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getChangeExamDate(session, selectExamDateForm, model);
		}
		session.setAttribute("newExamId", selectExamDateForm.getExamId());
		
		return "redirect:/confirm-booking-change";
	}
	
	@GetMapping("confirm-booking-change")
	public String getConfirmBookingChange(HttpSession session, Model model) {
		Integer examId = (Integer) session.getAttribute("newExamId");
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		Integer gradeId = (Integer) session.getAttribute("gradeId");
		
		ExamInfos examInfo = examInfosService.getExamInfo(examId);
		
		Fees fee = feesService.getFee(subjectId, gradeId);
		
		model.addAttribute("examInfo", examInfo);
		model.addAttribute("fee", fee);
		
		return "booking-change/confirm-booking-change";
	}
	
	@Transactional
	@PostMapping("/confirm-booking-change") 
	public String postConfirmBookingChange(HttpSession session, @AuthenticationPrincipal(expression = "id") Integer accountId) {
		Integer newExamId = (Integer) session.getAttribute("newExamId");
		
		Bookings booking = new Bookings();
		booking.setAccountId(accountId);
		booking.setExamId(newExamId);
		bookingsService.addBooking(booking);
		
		Integer oldExamId = (Integer) session.getAttribute("oldExamId");
		bookingsService.deleteBooking(accountId, oldExamId);
		
		session.removeAttribute("subjectId");
		session.removeAttribute("gradeId");
		session.removeAttribute("placeId");
		session.removeAttribute("newExamId");
		session.removeAttribute("oldExamId");
		
		return "redirect:/complete-booking-change";
	}
	
	@GetMapping("complete-booking-change") 
	public String getCompleteBookingChange() {
		return "booking-change/complete-booking-change";
	}
}