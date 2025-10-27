package language.exam.form;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;
@Data
 public class FeesListForm {
	
	@Valid
	private List<SubjectsForm> subjects;
}