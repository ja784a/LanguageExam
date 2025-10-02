package language.exam.domain.exams.model;

import java.util.List;

import lombok.Data;

@Data
public class Places {
	private Integer id;
	private String place;
	private List<ExamInfos> examInfoList;
}