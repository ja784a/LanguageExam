package language.exam.form;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class SubjectsForm {
	
	private Integer id;
	
	private String subject;
	
	@Valid
	private List<GradesForm> gradeList; 
}