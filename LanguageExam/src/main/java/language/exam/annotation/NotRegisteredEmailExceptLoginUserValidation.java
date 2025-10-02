package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.AccountsService;
import language.exam.security.CustomUserDetails;


public class NotRegisteredEmailExceptLoginUserValidation implements ConstraintValidator<NotRegisteredEmailExceptLoginUser, String>{
	
	private String message;
	
	public void initialize(NotRegisteredEmailExceptLoginUser annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private AccountsService accountsService;
	
	@Override
	public boolean isValid(String mail, ConstraintValidatorContext context) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		Integer id = 0;
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			id = userDetails.getId();
		}
		
		if (accountsService.isNotRegisteredEmailExceptLoginUser(mail, id)) {
			return true;
		}
		
		return false;
		
	}
}