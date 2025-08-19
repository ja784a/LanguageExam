package language.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Places;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.GradesService;
import language.exam.domain.exams.service.PlacesService;
import language.exam.domain.exams.service.SubjectsService;
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
	
	@GetMapping("/select-grade/{id}")
	public String getSelectGrade(@PathVariable Integer id, @ModelAttribute SelectGradeForm selectGradeForm, Model model) {
		Subjects subject = subjectsService.getSubject(id);
		
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
	
	@PostMapping("/select-grade")
	public String postSelectGrade(HttpSession session, SelectGradeForm selectGradeForm) {
		session.setAttribute("subjectId", selectGradeForm.getSubjectId());
		session.setAttribute("gradeId", selectGradeForm.getGradeId());
		
		return "redirect:/select-place";
	}
	
	@GetMapping("/select-place")
	public String getSelectPlace(HttpSession session, @ModelAttribute SelectPlaceForm selectPlaceForm, Model model) {
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
	public String postSelectPlace(HttpSession session, @ModelAttribute SelectPlaceForm selectPlaceForm, Model model) {
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
		
		List<ExamInfos> examDates = examInfosService.getExamInfosForUsers();
		model.addAttribute("examDates", examDates);
		
		return "select/select-exam-date";
	}
}