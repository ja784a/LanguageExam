package language.exam.domain.exams.model;

import lombok.Data;

@Data
public class Accounts {
	private Integer id;
	private String name;
	private String mail;
	private String pass;
	private String pref;
	private String city;
	private String town;
	private String building;
	private Integer role;
}