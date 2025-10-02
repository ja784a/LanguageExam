package language.exam.annotation;

import java.util.Calendar;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidDateValidation implements ConstraintValidator<ValidDate, Date>{
	
	private String message;
	
	public void initialize(ValidDate annotation) {
		this.message = annotation.message();
	}
	
	
	@Override
	public boolean isValid(Date examDate, ConstraintValidatorContext context) {
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 30);
		
		Date oneMonthLater = calendar.getTime();
		
		if (examDate.after(oneMonthLater)) {
			return true;
		}
		
		return false;
		
	}
}