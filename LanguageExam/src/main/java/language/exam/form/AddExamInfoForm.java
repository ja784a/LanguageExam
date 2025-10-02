package language.exam.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@ValidSubjectId
	private Integer subjectId;
	
	@ValidGradeId
	private Integer gradeId;
	
	@ValidDate
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date examDate;
	
	@ValidPlaceId
	private Integer placeId;
}