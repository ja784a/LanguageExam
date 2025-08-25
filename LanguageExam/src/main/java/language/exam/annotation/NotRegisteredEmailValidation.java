package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.AccountsService;


public class NotRegisteredEmailValidation implements ConstraintValidator<NotRegisteredEmail, String>{
	
	private String message;
	
	public void initialize(NotRegisteredEmail annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private AccountsService accountsService;
	
	@Override
	public boolean isValid(String mail, ConstraintValidatorContext context) {
		if (accountsService.isNotRegisteredMail(mail)) {
			return true;
		}
		
		return false;
		
	}
}