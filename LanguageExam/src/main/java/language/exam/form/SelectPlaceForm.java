package language.exam.form;

import jakarta.validation.constraints.NotNull;
import language.exam.annotation.ValidPlaceId;
import lombok.Data;

@Data
public class SelectPlaceForm {
	@NotNull(groups = ValidGroup1.class)
	@ValidPlaceId(groups = ValidGroup2.class)
	private Integer placeId;
	private Integer subjectId;
}