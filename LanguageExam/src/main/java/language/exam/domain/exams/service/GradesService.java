package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.Grades;

public interface GradesService {
	
	public List<Grades> getAllGrades();
	
	public Grades getGrade(Integer id);
	
	public boolean isValidGradeId(Integer gradeId);
	
	public List<Integer> getIds();
	
	public List<Grades> getExamDates(Integer subjectId);
}