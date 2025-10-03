package language.exam.domain.exams.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import language.exam.domain.exams.model.Grades;
import language.exam.domain.exams.service.GradesService;
import language.exam.repository.GradesMapper;

@Service
public class GradesServiceImpl implements GradesService {
	
	@Autowired
	private GradesMapper mapper;
	
	public List<Grades> getAllGrades() {
		return mapper.selectAllGrades();
	}
	
	public Grades getGrade(Integer id) {
		return mapper.selectGrade(id);
	}
	
	public boolean isValidGradeId(Integer gradeId) {
		if (mapper.countGrades(gradeId) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<Integer> getIds() {
		return mapper.selectIds();
	}
	
	public List<Grades> getExamDates(Integer subjectId) {
		return mapper.selectExamDates(subjectId);
	}
}