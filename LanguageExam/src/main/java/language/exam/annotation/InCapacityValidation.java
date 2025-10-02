package language.exam.annotation;


import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.BookingsService;


public class InCapacityValidation implements ConstraintValidator<InCapacity, Integer>{
	
	private String message;
	
	public void initialize(InCapacity annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private BookingsService bookingsService;
	
	@Override
	public boolean isValid(Integer examId, ConstraintValidatorContext context) {
		if (bookingsService.isInCapacity(examId)) {
			return true;
		}
		return false;
		
	}
}