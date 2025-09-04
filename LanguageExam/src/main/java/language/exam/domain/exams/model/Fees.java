package language.exam.domain.exams.model;

import lombok.Data;

@Data
public class Fees {
	private Integer id;
	private Integer subjectId;
	private Integer gradeId;
	private Integer fee;
	private Subjects subjects;
	private Grades grades;
}