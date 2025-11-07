package language.exam.form;

import jakarta.validation.constraints.NotNull;
import language.exam.annotation.ValidGradeId;
import lombok.Data;

@Data
public class SelectGradeForm {
	private Integer subjectId;
	
	@NotNull(groups = ValidGroup1.class)
	@ValidGradeId(groups = ValidGroup2.class)
	private Integer gradeId;
}