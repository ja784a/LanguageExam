package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.Grades;

public interface GradesService {
	
	public List<Grades> getAllGrades();
	
	public Grades getGrade(Integer id);
}