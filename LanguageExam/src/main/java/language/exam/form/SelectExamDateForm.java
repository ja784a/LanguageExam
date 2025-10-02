package language.exam.form;

import language.exam.annotation.InCapacity;
import language.exam.annotation.NotDuplicatedDate;
import language.exam.annotation.ValidExamId;
import lombok.Data;

@Data
public class SelectExamDateForm {
	
	@InCapacity(groups = ValidGroup1.class)
	@ValidExamId(groups = ValidGroup1.class)
	@NotDuplicatedDate(groups = ValidGroup2.class)
	private Integer examId;
}