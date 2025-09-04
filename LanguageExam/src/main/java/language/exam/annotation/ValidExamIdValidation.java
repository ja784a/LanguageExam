package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.ExamInfosService;


public class ValidExamIdValidation implements ConstraintValidator<ValidExamId, Integer>{
	
	private String message;
	
	public void initialize(ValidExamId annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Override
	public boolean isValid(Integer examId, ConstraintValidatorContext context) {
		if (examInfosService.isValidExamId(examId)) {
			return true;
		}
		
		return false;
		
	}
}