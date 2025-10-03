package language.exam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Infos;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.GradesService;
import language.exam.domain.exams.service.InfosService;
import language.exam.domain.exams.service.PlacesService;
import language.exam.domain.exams.service.SubjectsService;

@Controller
public class GuideController {
	
	@Autowired
	private SubjectsService subjectsService;
	
	@Autowired
	private GradesService gradesService;
	
	@Autowired
	private InfosService infosService;
	
	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@GetMapping("/exams-guide")
	public String getExamGuide(Model model) {
		List<Subjects> subjects = subjectsService.getAllSubjects();
		
		model.addAttribute("subjects", subjects);
		
		List<Grades> gradesList = gradesService.getAllGrades();
		
		String grades = "";
		
		for (Grades g : gradesList) {
			grades += g.getGrade() + " ";
		}
		
		model.addAttribute("grades", grades);
		
		List<Infos> infos = infosService.getAllInfos();

		model.addAttribute("infos", infos);
		
		return "guide/exams-guide"; 
	}
	
	@GetMapping("/exam-details/{id}")
	public String getExamDetails(@PathVariable Integer id, Model model) {
		Subjects subject = subjectsService.getSubject(id);
		
		model.addAttribute("subject", subject);
		
		List<Grades> gradeList = gradesService.getExamDates(id);
		
		
		
		String grades = "";
		
		for (Grades g : gradeList) {
			grades += g.getGrade() + " ";
		}
		
		model.addAttribute("grades", grades);
		
		model.addAttribute("gradeList", gradeList);
		
		
		
		return "guide/exam-details";
	}
}