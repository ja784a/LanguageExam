package language.exam.domain.exams.model;

import java.util.List;

import lombok.Data;

@Data
public class Grades {
	private Integer id;
	private String grade;
	private Fees fees;
	private List<Places> placeList; 
}