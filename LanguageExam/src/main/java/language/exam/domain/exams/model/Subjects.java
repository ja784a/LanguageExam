package language.exam.domain.exams.model;

import java.util.List;

import lombok.Data;

@Data
public class Subjects {
	private Integer id;
	private String subject;
	private List<Grades> gradeList;
}