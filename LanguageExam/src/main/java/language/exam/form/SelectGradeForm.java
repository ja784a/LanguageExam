package language.exam.form;

import language.exam.annotation.ValidGradeId;
import lombok.Data;

@Data
public class SelectGradeForm {
	private Integer subjectId;
	
	@ValidGradeId
	private Integer gradeId;
}