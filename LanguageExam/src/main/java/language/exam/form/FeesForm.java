package language.exam.form;

import java.util.List;

import language.exam.domain.exams.model.Subjects;
import lombok.Data;

@Data
public class FeesForm {
	private List<Subjects> subjects;
}