package language.exam.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.FeesService;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.form.FeesForm;

@Controller
public class FeesControllerForAdmin {
	@Autowired
	SubjectsService subjectsService;
	
	@Autowired
	FeesService feesService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/admin-fees-for-admin")
	public String getAdminFees(Model model) {
		List<Subjects> subjects = subjectsService.getFees();
		
		model.addAttribute("subjects", subjects);
		
		return "admin-fees/admin-fees";
	}
	
	@GetMapping("/edit-fees-for-admin")
	public String getEditFees(@ModelAttribute FeesForm feesForm, Model model) {
		List<Subjects> subjects = subjectsService.getFees();
		
		feesForm.setSubjects(subjects);
		
		model.addAttribute("subjects", subjects);
		
		return "admin-fees/edit-fees";
	}
	
	@PostMapping("/edit-fees-for-admin")
	public String postEditFees(FeesForm feesForm) {
		List<Subjects> subjects = feesForm.getSubjects();
		
		List<Fees> feesList = new ArrayList<>();
		
		for (Subjects s : subjects) {
			for (Grades g : s.getGradeList()) {
				feesList.add(g.getFees());
			}
		}
		
		feesService.updateFees(feesList);
		
		return "redirect:/admin-fees-for-admin";
	}
}