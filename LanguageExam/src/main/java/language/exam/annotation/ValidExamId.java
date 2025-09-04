package language.exam.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Documented
@Constraint(validatedBy = { ValidExamIdValidation.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExamId {
	String message() default "The exam id is not valid.";
	
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {}; 

	@Target(ElementType.FIELD) 
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		ValidExamId[] value();
	}
}