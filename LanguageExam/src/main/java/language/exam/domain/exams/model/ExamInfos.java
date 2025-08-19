package language.exam.domain.exams.model;

import java.util.Date;

import lombok.Data;

@Data
public class ExamInfos {
	private Integer id;
	private Integer subjectId;
	private Integer gradeId;
	private Date examDate;
	private Date oldDate;
	private Integer placeId;
	private String comments;
	private Integer cancel;
	private Subjects subjects;
	private Grades grades;
	private Places places;
}