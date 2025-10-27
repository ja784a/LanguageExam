package language.exam.controller;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.FeesService;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.form.FeesListForm;
import language.exam.form.SubjectsForm;

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
		
		for (Subjects s : subjects) {
			for (Grades g : s.getGradeList()) {
				if (g.getFees().getFee() == null) {
					return "error/error";
				}
			}
		}
		
		model.addAttribute("subjects", subjects);
		
		return "admin-fees/admin-fees";
	}
	
	@GetMapping("/edit-fees-for-admin")
	public String getEditFees(@ModelAttribute FeesListForm feesListForm, Model model) {
		List<Subjects> subjects = subjectsService.getFees();
		
		Type listType = new TypeToken<List<SubjectsForm>>(){}.getType();
		List<SubjectsForm> subjectsList = modelMapper.map(subjects, listType);
		feesListForm.setSubjects(subjectsList);
		model.addAttribute("subjects", subjects);
		
		return "admin-fees/edit-fees";
	}
	
	@PostMapping("/edit-fees-for-admin")
	public String postEditFees(@Validated FeesListForm feesListForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getEditFees(feesListForm, model);
		} else {
			List<Fees> feesList = new ArrayList<>();
			
			Type listType = new TypeToken<List<Subjects>>(){}.getType();
			List<Subjects> subjects = modelMapper.map(feesListForm.getSubjects(), listType);
			
			for (Subjects s : subjects) {
				for (Grades g : s.getGradeList()) {
					feesList.add(g.getFees());
				}
			}
			
			feesService.updateFees(feesList);
			
			return "redirect:/admin-fees-for-admin";
		}
	}
}