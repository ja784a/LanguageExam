package language.exam.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FeesForm {
	private Integer id;
	
	@NotNull
	@Max(1000000)
	@Min(0)
	private Integer fee;
}