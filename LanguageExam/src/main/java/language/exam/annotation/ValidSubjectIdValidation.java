package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.SubjectsService;


public class ValidSubjectIdValidation implements ConstraintValidator<ValidSubjectId, Integer>{
	
	private String message;
	
	public void initialize(ValidSubjectId annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private SubjectsService subjectsService;
	
	@Override
	public boolean isValid(Integer subjectId, ConstraintValidatorContext context) {
		if (subjectsService.isValidSubjectId(subjectId)) {
			return true;
		}
		
		return false;
		
	}
}