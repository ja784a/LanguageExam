package language.exam.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CancelExamFormForAdmin {
	private Integer id;
	
	@NotBlank
	@Size(max = 100)
	private String comments;
}