package language.exam.form;

import language.exam.annotation.ValidPlaceId;
import lombok.Data;

@Data
public class SelectPlaceForm {
	@ValidPlaceId
	private Integer placeId;
	private Integer subjectId;
}