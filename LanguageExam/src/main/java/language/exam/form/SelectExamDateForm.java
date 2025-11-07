package language.exam.form;

import jakarta.validation.constraints.NotNull;
import language.exam.annotation.InCapacity;
import language.exam.annotation.NotDuplicatedDate;
import language.exam.annotation.ValidExamId;
import lombok.Data;

@Data
public class SelectExamDateForm {
	@NotNull(groups = ValidGroup1.class)
	@InCapacity(groups = ValidGroup2.class)
	@ValidExamId(groups = ValidGroup2.class)
	@NotDuplicatedDate(groups = ValidGroup3.class)
	private Integer examId;
}