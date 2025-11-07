package language.exam.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import language.exam.annotation.ValidDate;
import lombok.Data;

@Data
public class ChangeExamDateForm {
	private Integer id;
	
	@NotNull
	@ValidDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date oldDate;

	@NotBlank
	@Size(max = 100)
	private String comments;
	
}