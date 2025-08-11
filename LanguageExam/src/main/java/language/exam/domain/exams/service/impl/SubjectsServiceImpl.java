package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Subjects;
import language.exam.domain.exams.service.SubjectsService;
import language.exam.repository.SubjectsMapper;

@Service
public class SubjectsServiceImpl implements SubjectsService {
	
	@Autowired
	private SubjectsMapper mapper;
	
	public List<Subjects> getAllSubjects() {
		return mapper.selectAllSubjects();
	}
	
	public Subjects getSubject(Integer id) {
		return mapper.selectSubject(id);
	}
}