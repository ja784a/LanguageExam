package language.exam.annotation;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.model.ExamInfos;
import language.exam.domain.exams.service.BookingsService;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.security.CustomUserDetails;


public class NotDuplicatedDateValidation implements ConstraintValidator<NotDuplicatedDate, Integer>{
	
	private String message;
	
	public void initialize(NotDuplicatedDate annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Autowired
	private BookingsService bookingsService;
	
	@Autowired
	private UserDetailsService userDetails;
	
	
	
	@Override
	public boolean isValid(Integer examId, ConstraintValidatorContext context) {
		ExamInfos examInfo = examInfosService.getExamInfo(examId);
		Date examDate = examInfo.getExamDate();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		Integer accountId = 0;
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			accountId = userDetails.getId();
		}
		
		if (bookingsService.isNotDuplicatedDate(examDate, accountId)) {
			return true;
		}
		return false;
		
	}
}