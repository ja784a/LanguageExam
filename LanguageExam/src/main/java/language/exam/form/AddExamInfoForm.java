package language.exam.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import language.exam.annotation.NotDuplicatedExam;
import language.exam.annotation.ValidDate;
import language.exam.annotation.ValidGradeId;
import language.exam.annotation.ValidPlaceId;
import language.exam.annotation.ValidSubjectId;
import lombok.Data;

@NotDuplicatedExam
@Data
public class AddExamInfoForm {
	private Integer id;
	
	@NotNull(groups = ValidGroup1.class)
	@ValidSubjectId(groups = ValidGroup2.class)
	private Integer subjectId;
	
	@NotNull(groups = ValidGroup1.class)
	@ValidGradeId(groups = ValidGroup2.class)
	private Integer gradeId;
	
	@NotNull(groups = ValidGroup1.class)
	@ValidDate(groups = ValidGroup2.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examDate;
	
	@NotNull(groups = ValidGroup1.class)
	@ValidPlaceId(groups = ValidGroup2.class)
	private Integer placeId;
}