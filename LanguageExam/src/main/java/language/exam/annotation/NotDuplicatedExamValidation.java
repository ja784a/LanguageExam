package language.exam.annotation;


import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import language.exam.domain.exams.service.ExamInfosService;
import language.exam.form.AddExamInfoForm;


public class NotDuplicatedExamValidation implements ConstraintValidator<NotDuplicatedExam, AddExamInfoForm>{
	
	private String message;
	
	public void initialize(NotDuplicatedExam annotation) {
		this.message = annotation.message();
	}
	
	@Autowired
	private ExamInfosService examInfosService;
	
	@Override
	public boolean isValid(AddExamInfoForm form, ConstraintValidatorContext context) {
		if (examInfosService.isNotDuplicatedExam(form.getSubjectId(), form.getGradeId(), form.getPlaceId(), form.getExamDate())) {
			return true;
		}
		return false;
		
	}
}