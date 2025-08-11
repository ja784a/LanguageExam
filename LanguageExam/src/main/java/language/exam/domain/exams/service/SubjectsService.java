package language.exam.domain.exams.service;

import java.util.List;

import language.exam.domain.exams.model.Subjects;

public interface SubjectsService {
	
	public List<Subjects> getAllSubjects();
	
	public Subjects getSubject(Integer id);
}