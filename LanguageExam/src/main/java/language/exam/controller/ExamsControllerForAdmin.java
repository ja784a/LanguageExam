package language.exam.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.model.Places;
import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.GradesService;
import language.exam.domain.exams.service.PlacesService;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.form.AddExamInfoForm;
import language.exam.form.CancelExamFormForAdmin;
import language.exam.form.ChangeExamDateForm;
import language.exam.form.GroupOrder;

@Controller
public class ExamsControllerForAdmin {
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private SubjectsService subjectsService;
	
	@Autowired
	private GradesService gradesService;
	
	@Autowired
	private PlacesService placesService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/admin-exams-for-admin")
	public String getAdminExams(@RequestParam(defaultValue = "0" )int page, Model model) {
		final int size = 6;
		int offset = page * size;
		
		Pageable pageable = PageRequest.of(page, size);
		
		int allExams = examInfosService.countExamInfosForAdmin();
		
		if (allExams == 0) {
			allExams = 1;
		}
		
		List<ExamInfos> examInfos = examInfosService.getExamInfosForAdmin(size, offset);
		
		Page<ExamInfos> resultPage = new PageImpl<>(examInfos, pageable, allExams);
		
		if (page >= resultPage.getTotalPages() ||  page < 0) {
			return "error/error";
		} else {
			model.addAttribute("examInfos", examInfos);
			model.addAttribute("resultPage", resultPage);
		}
		
		return "admin-exams/admin-exams";
		
	}
	
	@GetMapping("/add-exam-for-admin")
	public String getAddExam(@ModelAttribute AddExamInfoForm addExamForm, Model model) {
		List<Subjects> subjects = subjectsService.getAllSubjects();
		model.addAttribute("subjects", subjects);
		
		List<Grades> grades = gradesService.getAllGrades();
		model.addAttribute("grades", grades);
		
		List<Places> places = placesService.getAllPlaces();
		model.addAttribute("places", places);
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 31);
		
		Date afterOneMonth = calendar.getTime();
		
		addExamForm.setExamDate(afterOneMonth);
		
		model.addAttribute("afterOneMonth", afterOneMonth);		
		return "admin-exams/add-exam";
	}
	
	@PostMapping("/add-exam-for-admin")
	public String postAddExam(@Validated AddExamInfoForm addExamInfoForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return getAddExam(addExamInfoForm, model);
		} else {
			ExamInfos examInfos = modelMapper.map(addExamInfoForm, ExamInfos.class);		
			examInfosService.addExamInfo(examInfos);
			
			return "redirect:/admin-exams-for-admin";
		}
	}
	
	@GetMapping("/change-exam-date-for-admin/{id}")
	public String getChangeExamDate(@PathVariable("id") Integer id, @ModelAttribute ChangeExamDateForm changeExamDateForm, Model model) {
		ExamInfos examInfo = examInfosService.getExamInfo(id);
		
		if (examInfo == null) {
			return "error/error";
		} else {
			if (changeExamDateForm.getExamDate() == null) {
				changeExamDateForm.setOldDate(examInfo.getExamDate());
				changeExamDateForm.setId(id);
				changeExamDateForm.setExamDate(examInfo.getExamDate());	
				changeExamDateForm.setComments(examInfo.getComments());
			}
			model.addAttribute("examInfo", examInfo);
			
			return "admin-exams/change-exam-date";
		}
	}
	
	@PostMapping("/change-exam-date-for-admin/{id}")
	public String postChangeExamDate(@PathVariable("id") Integer id, @Validated(GroupOrder.class)  ChangeExamDateForm changeExamDateForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return getChangeExamDate(id, changeExamDateForm, model);
		} else {
			ExamInfos examInfo = modelMapper.map(changeExamDateForm, ExamInfos.class);
			examInfosService.updateExamInfo(examInfo);
			
			return "redirect:/admin-exams-for-admin";
		}
	}
	
	@GetMapping("/cancel-exam-for-admin/{id}")
	public String getCancelExam(@ModelAttribute CancelExamFormForAdmin cancelExamFormForAdmin, Model model, @PathVariable("id") Integer id) {
		ExamInfos examInfo = examInfosService.getExamInfo(id);
		
		if (examInfo == null) {
			return "error/error";
		} else {
			model.addAttribute("examInfo", examInfo);
			
			cancelExamFormForAdmin.setComments(examInfo.getComments());
			
			return "admin-exams/cancel-exam";
		}
	}
	
	@PostMapping("/cancel-exam-for-admin/{id}")
	public String postCancelExam(@Validated CancelExamFormForAdmin cancelExamFormForAdmin, BindingResult result, @PathVariable Integer id, Model model) {
		if (result.hasErrors()) {
			return getCancelExam(cancelExamFormForAdmin, model, id);
		} else {
			ExamInfos examInfo = modelMapper.map(cancelExamFormForAdmin, ExamInfos.class);
			examInfosService.updateCancel(examInfo);
			
			return "redirect:/admin-exams-for-admin";
		}
	}
}