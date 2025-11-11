package language.exam.domain.exams.model;

import java.util.Date;

import lombok.Data;

@Data
public class Bookings {
	private Integer id;
	private Integer accountId;
	private Integer examId;
	private Date insertedDateTime;
	private ExamInfos examInfos;
	private Subjects subjects;
	private Grades grades;
	private Places places;
}