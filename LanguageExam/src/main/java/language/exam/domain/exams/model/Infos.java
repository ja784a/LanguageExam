package language.exam.domain.exams.model;

import java.util.Date;

import lombok.Data;

@Data
public class Infos {
	private Integer id;
	private Date postDate;
	private String title;
	private String content;
}