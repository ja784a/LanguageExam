package language.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Places;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.FeesService;
import language.exam.domain.exams.service.GradesService;
import language.exam.domain.exams.service.PlacesService;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.form.GroupOrder;
import language.exam.form.SelectExamDateForm;
import language.exam.form.SelectGradeForm;
import language.exam.form.SelectPlaceForm;

@Controller
public class SelectController {
	
	@Autowired
	private SubjectsService subjectsService;
	
	@Autowired
	private GradesService gradesService;
	
	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private FeesService feesService;
	
	@GetMapping("/select-grade")
	public String getSelectGrade(HttpSession session, @ModelAttribute SelectGradeForm selectGradeForm, Model model) {
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		
		Subjects subject = subjectsService.getSubject(subjectId);
		
		if (subject == null) {
			return "error/error";
		} else {
			model.addAttribute("subject", subject);
			selectGradeForm.setSubjectId(subject.getId());
			
			List<Grades> gradesList = gradesService.getAllGrades();
			String grades = "";
			for (Grades g : gradesList) {
				grades += g.getGrade() + " ";
			}
			
			model.addAttribute("grades", grades);
			
			model.addAttribute("gradesList", gradesList);
			
			return "select/select-grade";
		}
	}
	
	@PostMapping("/call-select-grade")
	public String postCallSelectgrade(HttpSession session, @RequestParam("subjectId") Integer subjectId, Model model) {
		session.setAttribute("subjectId", subjectId);
		
		return "redirect:/select-grade";
	}
	
	@PostMapping("/select-grade")
	public String postSelectGrade(HttpSession session, @Validated(GroupOrder.class) SelectGradeForm selectGradeForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			
			return getSelectGrade(session, selectGradeForm, model);
		}
		session.setAttribute("subjectId", selectGradeForm.getSubjectId());
		session.setAttribute("gradeId", selectGradeForm.getGradeId());
		
		return "redirect:/select-place";
	}
	
	@GetMapping("/select-place")
	public String getSelectPlace(HttpSession session, @ModelAttribute SelectPlaceForm selectPlaceForm,Model model) {
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		Subjects subject = subjectsService.getSubject(subjectId);
		model.addAttribute("subject", subject);
		
		Integer gradeId = (Integer) session.getAttribute("gradeId");
		Grades grade = gradesService.getGrade(gradeId);
		model.addAttribute("grade", grade);
		
		List<Places> places = placesService.getAllPlaces();
		model.addAttribute("places", places);
		
		return "select/select-place";
	}
	
	@PostMapping("/select-place")
	public String postSelectPlace(HttpSession session, @ModelAttribute @Validated(GroupOrder.class) SelectPlaceForm selectPlaceForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getSelectPlace(session, selectPlaceForm, model);
		}
		session.setAttribute("placeId", selectPlaceForm.getPlaceId());
		
		return "redirect:/select-exam-date";
	}
	
	@GetMapping("/select-exam-date")
	public String getSelectExamDate(HttpSession session, @ModelAttribute SelectExamDateForm selectExamDateForm, Model model) {
		Integer subjectId = (Integer) session.getAttribute("subjectId");
		Subjects subject = subjectsService.getSubject(subjectId);
		model.addAttribute("subject", subject);
		
		Integer gradeId = (Integer) session.getAttribute("gradeId");
		Grades grade = gradesService.getGrade(gradeId);
		model.addAttribute("grade", grade);
		
		Integer placeId = (Integer) session.getAttribute("placeId");
		Places place = placesService.getPlace(placeId);
		model.addAttribute("place", place);
		
		List<ExamInfos> examDates = examInfosService.getExamInfosForUsers(subjectId, gradeId, placeId);
		model.addAttribute("examDates", examDates);
		
		return "select/select-exam-date";
	}
	
	@PostMapping("/select-exam-date")
	public String postSelectExamDate(HttpSession session, @Validated(GroupOrder.class) SelectExamDateForm selectExamDateForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getSelectExamDate(session, selectExamDateForm, model);
		}
		session.setAttribute("examId", selectExamDateForm.getExamId());
		
		return "redirect:/confirm-booking";
	}
}