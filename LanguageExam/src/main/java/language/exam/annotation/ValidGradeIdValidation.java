package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.GradesService;


public class ValidGradeIdValidation implements ConstraintValidator<ValidGradeId, Integer>{
	
	private String message;
	
	public void initialize(ValidGradeId annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private GradesService gradesService;
	
	@Override
	public boolean isValid(Integer gradeId, ConstraintValidatorContext context) {
		if (gradesService.isValidGradeId(gradeId)) {
			return true;
		}
		
		return false;
		
	}
}