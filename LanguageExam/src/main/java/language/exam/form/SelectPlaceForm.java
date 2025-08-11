package language.exam.form;

import language.exam.domain.exams.model.Places;
import lombok.Data;

@Data
public class SelectPlaceForm {
	private Places place;
}