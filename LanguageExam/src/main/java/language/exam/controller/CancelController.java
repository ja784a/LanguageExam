package language.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.model.Fees;
import language.exam.domain.exams.service.BookingsService;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.domain.exams.service.FeesService;
import language.exam.form.CancelExamForm;

@Controller
public class CancelController {
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private FeesService feesService;
	
	@Autowired
	private BookingsService bookingsService;
	
	@GetMapping("confirm-cancel-exam/{id}")
	public String getCancelCancelExam(@PathVariable("id") Integer examId, @ModelAttribute CancelExamForm cancelExamForm, Model model) {
		ExamInfos examInfo = examInfosService.getExamInfo(examId);
		model.addAttribute("examInfo", examInfo);
		
		Fees fee = feesService.getFee(examInfo.getSubjectId(), examInfo.getGradeId());
		model.addAttribute("fee", fee);
		
		cancelExamForm.setExamId(examId);
		
		return "cancel/confirm-cancel-exam";
	}
	
	@PostMapping("confirm-cancel-exam")
	public String postCancelExam(CancelExamForm cancelExamForm,@AuthenticationPrincipal(expression = "id") Integer accountId) {
		cancelExamForm.setAccountId(accountId);
		bookingsService.deleteBooking(cancelExamForm.getAccountId(), cancelExamForm.getExamId());
		
		return "redirect:/complete-cancel";
	}
	
	@GetMapping("complete-cancel")
	public String getCompleteCancel() {
		return "cancel/complete-cancel";
	}
}