package language.exam.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Documented
@Constraint(validatedBy = {NotDuplicatedExamValidation.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDuplicatedExam {
	String message() default "The exam is duplicated with others in suject, grade, place, and date.";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {}; 

	@Target(ElementType.TYPE ) 
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		NotDuplicatedExam[] value();
	}
}