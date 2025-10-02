package language.exam.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.PlacesService;


public class ValidPlaceIdValidation implements ConstraintValidator<ValidPlaceId, Integer>{
	
	private String message;
	
	public void initialize(ValidPlaceId annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private PlacesService placesService;
	
	@Override
	public boolean isValid(Integer placeId, ConstraintValidatorContext context) {
		if (placesService.isValidPlaceId(placeId)) {
			return true;
		}
		
		return false;
		
	}
}