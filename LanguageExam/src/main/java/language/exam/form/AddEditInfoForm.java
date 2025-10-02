package language.exam.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddEditInfoForm {
	private Integer id;
	
	@NotBlank
	@Size(max = 20)
	private String title;
	
	@NotBlank
	@Size(max = 500)
	private String content;
}