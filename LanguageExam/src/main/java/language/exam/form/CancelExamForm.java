package language.exam.form;

import lombok.Data;

@Data
public class CancelExamForm {
	private Integer examId;
	private Integer accountId;
}