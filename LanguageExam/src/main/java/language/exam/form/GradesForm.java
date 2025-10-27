package language.exam.form;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GradesForm {
	
	private Integer id;
	
	private String grade;
	
	@Valid
	private FeesForm fees;
}